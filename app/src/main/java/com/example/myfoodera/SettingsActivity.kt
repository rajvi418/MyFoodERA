package com.example.myfoodera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val switchNotification = findViewById<Switch>(R.id.switchNotification)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        switchNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                Toast.makeText(this, "Notifications Enabled", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Notifications Disabled", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_settings

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
                R.id.nav_settings ->  true

                else -> false
            }
        }


    }
}