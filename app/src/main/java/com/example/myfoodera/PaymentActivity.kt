package com.example.myfoodera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

// 🔹 Firebase imports (UNCOMMENT LATER)
// import com.google.firebase.database.DatabaseReference
// import com.google.firebase.database.FirebaseDatabase

class PaymentActivity : AppCompatActivity() {

    private lateinit var paymentGroup: RadioGroup
    private lateinit var btnPayNow: Button

    // 🔹 Firebase Database reference (UNCOMMENT LATER)
    // private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // 🔹 Firebase connection (UNCOMMENT LATER)
        /*
        database = FirebaseDatabase.getInstance().reference
        */

        paymentGroup = findViewById(R.id.paymentGroup)
        btnPayNow = findViewById(R.id.btnPayNow)

        // Bottom Navigation
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

        btnPayNow.setOnClickListener {

            val selectedId = paymentGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please select payment method", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadio = findViewById<RadioButton>(selectedId)
            selectedRadio.text.toString()

            // 🔹 Save order to Firebase later
            /*
            val orderId = database.child("orders").push().key

            val orderData = HashMap<String, Any>()
            orderData["paymentMethod"] = paymentMethod
            orderData["status"] = "waiting"
            orderData["time"] = System.currentTimeMillis()

            database.child("orders").child(orderId!!).setValue(orderData)
            */

            // ✅ OPEN WAITING SCREEN
            startActivity(Intent(this, WaitingConfirmationActivity::class.java))

        }

    }
}