package com.example.myfoodera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SupportActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etMessage: EditText
    lateinit var btnSendFeedback: Button
    lateinit var btnEmailSupport: Button
    lateinit var profileIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        // Input fields
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etMessage = findViewById(R.id.etMessage)

        // Buttons
        btnSendFeedback = findViewById(R.id.btnSendFeedback)
        btnEmailSupport = findViewById(R.id.btnEmailSupport)

        // Profile icon
        profileIcon = findViewById(R.id.profileIcon)

        profileIcon.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }

        // SEND FEEDBACK
        btnSendFeedback.setOnClickListener {

            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val message = etMessage.text.toString()

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:undhadrajviii@gmail.com")

            intent.putExtra(Intent.EXTRA_SUBJECT, "MyFoodERA App Feedback")

            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Name: $name\nEmail: $email\n\nMessage:\n$message"
            )

            startActivity(Intent.createChooser(intent, "Send Email"))
        }

        // EMAIL SUPPORT BUTTON
        btnEmailSupport.setOnClickListener {

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:undhadrajviii@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "MyFoodERA Support")

            startActivity(Intent.createChooser(intent, "Contact Support"))
        }

        // Profile Click
        findViewById<ImageView>(R.id.profileIcon).setOnClickListener {

            startActivity(Intent(this, UserProfileActivity::class.java))

        }

        // Bottom Navigation
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
}
