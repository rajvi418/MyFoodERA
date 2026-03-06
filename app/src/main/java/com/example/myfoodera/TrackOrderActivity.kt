package com.example.myfoodera

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class TrackOrderActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var icConfirmed: ImageView
    private lateinit var icPreparing: ImageView
    private lateinit var icOutDelivery: ImageView
    private lateinit var icDelivered: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_order)

        // Initialize views
        progressBar = findViewById(R.id.orderProgressBar)
        icConfirmed = findViewById(R.id.icConfirmed)
        icPreparing = findViewById(R.id.icPreparing)
        icOutDelivery = findViewById(R.id.icOutDelivery)
        icDelivered = findViewById(R.id.icDelivered)

        // Simulate order status from admin panel
        val currentStatus = intent.getStringExtra("orderStatus") ?: "confirmed"
        updateOrderStatus(currentStatus)

        // Bottom navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> true
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

    private fun updateOrderStatus(status: String) {
        when (status.lowercase()) {
            "confirmed" -> {
                progressBar.progress = 25
                icConfirmed.setImageResource(R.drawable.ic_check_circle_orange)
            }

            "preparing" -> {
                progressBar.progress = 50
                icConfirmed.setImageResource(R.drawable.ic_check_circle_orange)
                icPreparing.setImageResource(R.drawable.ic_timer_orange)
            }

            "out_for_delivery" -> {
                progressBar.progress = 75
                icConfirmed.setImageResource(R.drawable.ic_check_circle_orange)
                icPreparing.setImageResource(R.drawable.ic_timer_orange)
                icOutDelivery.setImageResource(R.drawable.ic_delivery_orange)
            }

            "delivered" -> {
                progressBar.progress = 100
                icConfirmed.setImageResource(R.drawable.ic_check_circle_orange)
                icPreparing.setImageResource(R.drawable.ic_timer_orange)
                icOutDelivery.setImageResource(R.drawable.ic_delivery_orange)
                icDelivered.setImageResource(R.drawable.ic_home_orange)
            }
        }
    }
}