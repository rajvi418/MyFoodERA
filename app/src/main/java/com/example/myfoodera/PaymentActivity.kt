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

        // 🔹 Firebase connection (UNCOMMENT WHEN FIREBASE ADDED)
        /*
        database = FirebaseDatabase.getInstance().reference

        Example Firebase Structure:

        payment_methods
            cod : true
            upi : true
            card : false

        orders
            orderId
                userId
                paymentMethod
                status
                time
        */

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

        paymentGroup = findViewById(R.id.paymentGroup)
        btnPayNow = findViewById(R.id.btnPayNow)

        // 🔹 Later you can LOAD payment methods from Firebase
        /*
        database.child("payment_methods").get().addOnSuccessListener {

            val codEnabled = it.child("cod").value as Boolean
            val upiEnabled = it.child("upi").value as Boolean
            val cardEnabled = it.child("card").value as Boolean

            findViewById<RadioButton>(R.id.rbCod).isEnabled = codEnabled
            findViewById<RadioButton>(R.id.rbUpi).isEnabled = upiEnabled
            findViewById<RadioButton>(R.id.rbCard).isEnabled = cardEnabled
        }
        */

        btnPayNow.setOnClickListener {

            val selectedId = paymentGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please select payment method", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadio = findViewById<RadioButton>(selectedId)
            val paymentMethod = selectedRadio.text.toString()

            Toast.makeText(this, "Selected: $paymentMethod", Toast.LENGTH_SHORT).show()

            // 🔹 Fake Payment (No real gateway)
            // Only show success

            // 🔹 Save order to Firebase (LATER)
            /*
            val orderId = database.child("orders").push().key

            val orderData = HashMap<String, Any>()
            orderData["paymentMethod"] = paymentMethod
            orderData["status"] = "Paid"
            orderData["time"] = System.currentTimeMillis()

            database.child("orders").child(orderId!!).setValue(orderData)
            */

            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_LONG).show()

            // 🔹 Open Order Success Page
            startActivity(Intent(this, OrderSuccessActivity::class.java))

        }

    }

}