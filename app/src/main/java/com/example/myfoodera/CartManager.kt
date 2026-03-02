package com.example.myfoodera

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartManager {

    private const val PREF_NAME = "cart_pref"
    private const val KEY_CART = "cart_data"

    fun getCart(context: Context): MutableList<CartItem> {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString(KEY_CART, null)

        return if (json != null) {
            val type = object : TypeToken<MutableList<CartItem>>() {}.type
            Gson().fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    fun saveCart(context: Context, cart: MutableList<CartItem>) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val json = Gson().toJson(cart)
        editor.putString(KEY_CART, json)
        editor.apply()
    }

    fun addItem(context: Context, item: CartItem) {
        val cart = getCart(context)

        val existing = cart.find { it.name == item.name }

        if (existing != null) {
            existing.quantity += item.quantity
        } else {
            cart.add(item)
        }

        saveCart(context, cart)
    }

    fun clear(context: Context) {
        saveCart(context, mutableListOf())
    }

    fun getTotal(context: Context): Int {
        return getCart(context).sumOf { it.price * it.quantity }
    }
}