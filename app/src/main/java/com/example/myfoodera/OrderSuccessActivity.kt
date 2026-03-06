package com.example.myfoodera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random

class OrderSuccessActivity : AppCompatActivity() {

    private lateinit var txtOrderId: TextView
    private lateinit var btnBackHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        txtOrderId = findViewById(R.id.txtOrderId)
        btnBackHome = findViewById(R.id.btnBackHome)

        // Generate Random Order ID
        val orderNumber = Random.nextInt(10000, 99999)
        txtOrderId.text = "Order ID: #FD$orderNumber"

        btnBackHome.setOnClickListener {

            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

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

                R.id.nav_cart -> {

                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }

                R.id.nav_settings -> {

                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
}