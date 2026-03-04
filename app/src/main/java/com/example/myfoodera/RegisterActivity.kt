package com.example.myfoodera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etMobile: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLoginRedirect: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Bind Views
        etFullName = findViewById(R.id.etFullName)
        etSurname = findViewById(R.id.etSurname)
        etMobile = findViewById(R.id.etMobile)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        tvLoginRedirect = findViewById(R.id.tvLoginRedirect)

        // Register Button Click
        btnRegister.setOnClickListener {
            val fullName = etFullName.text.toString()
            val surname = etSurname.text.toString()
            val mobile = etMobile.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (fullName.isEmpty() || surname.isEmpty() || mobile.isEmpty() ||
                email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Add Firebase / DB registration code here
            Toast.makeText(this, "Registration Success! (DB code pending)", Toast.LENGTH_SHORT)
                .show()
        }

        // Login Redirect Click
        tvLoginRedirect.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}