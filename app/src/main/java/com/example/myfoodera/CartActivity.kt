package com.example.myfoodera

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartActivity : AppCompatActivity() {

    private lateinit var txtItems: TextView
    private lateinit var txtTotal: TextView
    private lateinit var btnIncrease: ImageView
    private lateinit var btnDecrease: ImageView
    private lateinit var btnRemove: ImageView
    private lateinit var btnClear: TextView

    private var selectedIndex = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        txtItems = findViewById(R.id.txtCartItems)
        txtTotal = findViewById(R.id.txtTotal)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnDecrease = findViewById(R.id.btnDecrease)
        btnRemove = findViewById(R.id.btnRemove)
        btnClear = findViewById(R.id.btnClear)

        updateCartUI()

        btnClear.setOnClickListener {
            CartManager.clear(this)
            updateCartUI()
        }

        btnIncrease.setOnClickListener {
            val cart = CartManager.getCart(this)
            if (cart.isNotEmpty()) {
                cart[selectedIndex].quantity++
                CartManager.saveCart(this, cart)
                updateCartUI()
            }
        }

        btnDecrease.setOnClickListener {
            val cart = CartManager.getCart(this)
            if (cart.isNotEmpty()) {
                val item = cart[selectedIndex]

                if (item.quantity > 1) {
                    item.quantity--
                } else {
                    cart.removeAt(selectedIndex)
                    if (selectedIndex > 0) selectedIndex--
                }

                CartManager.saveCart(this, cart)
                updateCartUI()
            }
        }

        btnRemove.setOnClickListener {
            val cart = CartManager.getCart(this)
            if (cart.isNotEmpty()) {
                cart.removeAt(selectedIndex)
                if (selectedIndex > 0) selectedIndex--
                CartManager.saveCart(this, cart)
                updateCartUI()
            }
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_cart

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }

                R.id.nav_fav -> {
                    startActivity(Intent(this, FavoriteActivity::class.java))
                    true
                }

                R.id.nav_cart -> true

                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    private fun updateCartUI() {

        val cart = CartManager.getCart(this)

        if (cart.isEmpty()) {
            txtItems.text = "Cart is Empty"
            txtTotal.text = "Total: ₹0"
            return
        }

        val itemsText = cart.joinToString("\n\n") {
            "${it.name}\nPrice: ₹${it.price}\nQuantity: ${it.quantity}\nSubtotal: ₹${it.price * it.quantity}"
        }

        txtItems.text = itemsText
        txtTotal.text = "Total: ₹${CartManager.getTotal(this)}"
    }
}