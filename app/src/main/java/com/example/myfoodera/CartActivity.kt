package com.example.myfoodera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val txtItems = findViewById<TextView>(R.id.txtCartItems)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val btnClear = findViewById<Button>(R.id.btnClear)

        val itemsText = CartManager.cartList.joinToString("\n") {
            "${it.name} - ₹${it.price}"
        }

        txtItems.text = itemsText
        txtTotal.text = "Total: ₹${CartManager.getTotalPrice()}"

        btnClear.setOnClickListener {
            CartManager.clearCart()
            txtItems.text = ""
            txtTotal.text = "Total: ₹0"
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home

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
}