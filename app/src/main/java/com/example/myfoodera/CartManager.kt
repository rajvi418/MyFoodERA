package com.example.myfoodera

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartManager {

    private const val PREF_NAME = "cart_pref"
    private const val KEY_CART = "cart_data"

    fun getCart(context: Context): ArrayList<CartItem> {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString(KEY_CART, null)

        return if (json != null) {
            val type = object : TypeToken<ArrayList<CartItem>>() {}.type
            Gson().fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

    fun saveCart(context: Context, cart: ArrayList<CartItem>) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().putString(KEY_CART, Gson().toJson(cart)).apply()
    }

    fun addToCart(context: Context, item: CartItem) {
        val cart = getCart(context)

        val existing = cart.find { it.name == item.name }

        if (existing != null) {
            existing.quantity += item.quantity
        } else {
            cart.add(item)
        }

        saveCart(context, cart)
    }

    fun increaseQuantity(context: Context, position: Int) {
        val cart = getCart(context)
        cart[position].quantity++
        saveCart(context, cart)
    }

    fun decreaseQuantity(context: Context, position: Int) {
        val cart = getCart(context)

        if (cart[position].quantity > 1) {
            cart[position].quantity--
        } else {
            cart.removeAt(position)
        }

        saveCart(context, cart)
    }

    fun removeItem(context: Context, position: Int) {
        val cart = getCart(context)
        cart.removeAt(position)
        saveCart(context, cart)
    }

    fun getTotal(context: Context): Int {
        return getCart(context).sumOf { it.price * it.quantity }
    }
}