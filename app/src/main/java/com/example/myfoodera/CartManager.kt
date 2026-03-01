package com.example.myfoodera

object CartManager {

    val cartList = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        cartList.add(item)
    }

    fun getTotalPrice(): Int {
        return cartList.sumOf { it.price }
    }

    fun clearCart() {
        cartList.clear()
    }
}