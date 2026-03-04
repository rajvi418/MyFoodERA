package com.example.myfoodera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsActivity : AppCompatActivity() {

    private lateinit var switchNotifications: Switch
    private lateinit var switchEmail: Switch
    private lateinit var layoutProfile: LinearLayout
    private lateinit var layoutChangePassword: LinearLayout
    private lateinit var layoutTerms: LinearLayout
    private lateinit var layoutPrivacy: LinearLayout
    private lateinit var layoutAbout: LinearLayout
    private lateinit var layoutSupport: LinearLayout
    private lateinit var layoutRate: LinearLayout
    private lateinit var btnSave: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Find views
        switchNotifications = findViewById(R.id.switchNotifications)
        switchEmail = findViewById(R.id.switchEmail)
        layoutProfile = findViewById(R.id.layoutProfile)
        layoutChangePassword = findViewById(R.id.layoutChangePassword)
        layoutTerms = findViewById(R.id.layoutTerms)
        layoutPrivacy = findViewById(R.id.layoutPrivacy)
        layoutAbout = findViewById(R.id.layoutAboutUs)
        layoutSupport = findViewById(R.id.layoutSupport)
        layoutRate = findViewById(R.id.layoutRateApp)
        btnSave = findViewById(R.id.btnSaveSettings)
        btnLogout = findViewById(R.id.btnLogout)

        // Profile click
        layoutProfile.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }

        // Change Password
        layoutChangePassword.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_change_password, null)
            val etOld = view.findViewById<EditText>(R.id.etOldPassword)
            val etNew = view.findViewById<EditText>(R.id.etNewPassword)
            val etConfirm = view.findViewById<EditText>(R.id.etConfirmPassword)
            val tvError = view.findViewById<TextView>(R.id.tvPasswordError)
            val tvForget = view.findViewById<TextView>(R.id.tvForgetPassword)

            val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("Save", null)
                .setNegativeButton("Cancel") { d, _ -> d.dismiss() }
                .create()

            // Forget password using Security Question / PIN
            tvForget.setOnClickListener {
                showSecurityQuestionDialog()
            }

            dialog.setOnShowListener {
                val btnSave = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                btnSave.setOnClickListener {
                    val oldPass = etOld.text.toString()
                    val newPass = etNew.text.toString()
                    val confirmPass = etConfirm.text.toString()

                    tvError.visibility = TextView.GONE

                    // 1️⃣ Check empty fields
                    if (oldPass.isBlank() || newPass.isBlank() || confirmPass.isBlank()) {
                        tvError.text = "All fields are required"
                        tvError.visibility = TextView.VISIBLE
                        return@setOnClickListener
                    }

                    // 2️⃣ Check new password match
                    if (newPass != confirmPass) {
                        tvError.text = "New password does not match confirmation"
                        tvError.visibility = TextView.VISIBLE
                        return@setOnClickListener
                    }

                    // 3️⃣ Password strength
                    val pattern =
                        Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+\$).{8,}\$")
                    if (!pattern.matches(newPass)) {
                        tvError.text =
                            "Password must be 8+ chars, with uppercase, lowercase, number & special char"
                        tvError.visibility = TextView.VISIBLE
                        return@setOnClickListener
                    }

                    // 4️⃣ TODO: Verify old password from database
                    // if (!database.verifyOldPassword(userId, oldPass)) { ... }

                    // 5️⃣ TODO: Update password in database
                    // database.updatePassword(userId, newPass)

                    Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        // Terms & Conditions
        layoutTerms.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Terms & Conditions")
                .setMessage(
                    "By accessing and using this application, you agree to comply with and be bound by these Terms and Conditions. The content, services, and functionalities provided within the app are intended for personal, non-commercial use unless otherwise stated. Users are responsible for maintaining the confidentiality of their account information and for all activities that occur under their account. Unauthorized use of the app, including but not limited to copying, distributing, modifying, or transmitting content without explicit permission, is strictly prohibited. The app developers reserve the right to update, modify, or remove any features, content, or functionalities at any time without prior notice. While we strive to ensure accurate and reliable information within the app, we do not guarantee the completeness, timeliness, or accuracy of the content and disclaim liability for any errors, omissions, or losses arising from use of the app. Users are expected to follow all applicable local, national, and international laws when interacting with the app, and any breach of these Terms may result in suspension or termination of access. The app may contain links to third-party websites, services, or resources, and we are not responsible for the content, policies, or practices of these external sites. By continuing to use the app, users acknowledge that they do so at their own risk, and the developers shall not be held liable for any direct, indirect, incidental, or consequential damages arising from the use of the app or reliance on its content."
                )
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // Privacy Policy
        layoutPrivacy.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Privacy Policy")
                .setMessage(
                    "Your privacy is extremely important to us, and this application is committed to protecting and responsibly managing your personal information. The app may collect certain data, including but not limited to device information, usage patterns, preferences, and interactions within the app, solely to enhance user experience, provide personalized content, and improve app performance. Personal data such as name, email address, or contact details are collected only when voluntarily provided by the user and are stored securely. We do not sell, rent, or share personal information with third parties for marketing purposes. The app may use cookies, analytics, or similar technologies to monitor app usage, gather anonymous statistics, and optimize services. Users have the right to access, modify, or request deletion of their personal information at any time by contacting our support. While we implement industry-standard security measures to protect user data, no system is completely secure, and users acknowledge the inherent risks of sharing information online. By using the app, users consent to the collection, storage, and use of data as described, and we may update this Privacy Policy periodically to reflect changes in legal requirements or app functionality."
                )
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // About / App Info
        layoutAbout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("About / App Info")
                .setMessage(
                    "MyFoodERA v1.0\nDeveloped by Rajvi Undhad,\n                         Mensi Vaghasiya, \n                         Mitva Lakkhani.\n\nThis application is designed to provide a seamless, intuitive, and engaging user experience, offering a range of features that cater to the needs and preferences of our audience. The app has been developed with a focus on reliability, usability, and aesthetic appeal, combining cutting-edge technology with a user-friendly interface to ensure that users can easily access the services and functionalities they require. Our goal is to deliver value by continuously updating the app, fixing bugs, and enhancing performance based on user feedback. The app may integrate third-party services and APIs to extend functionality, improve usability, or provide content relevant to users’ interests, always adhering to ethical and legal standards. We are committed to fostering trust and transparency, offering support and guidance to users who encounter issues or require assistance. By using this app, users can enjoy a convenient, efficient, and secure experience, benefiting from the thoughtfully designed features and services that have been built with care, attention to detail, and a long-term vision of creating an engaging digital environment that meets modern user expectations.   "
                )
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // Support & Feedback
        layoutSupport.setOnClickListener {
            startActivity(Intent(this, SupportActivity::class.java))
        }

        // Rate the App
        layoutRate.setOnClickListener {
            Toast.makeText(this, "Redirect to Play Store to rate app", Toast.LENGTH_SHORT).show()
        }

        // Save Settings
        btnSave.setOnClickListener {
            val push = switchNotifications.isChecked
            val email = switchEmail.isChecked

            // TODO: Save notification settings to database or shared preferences
            Toast.makeText(this, "Settings saved\nPush: $push\nEmail: $email", Toast.LENGTH_SHORT)
                .show()
        }

        // Logout
        btnLogout.setOnClickListener {
            // TODO: Clear user session / database logout logic
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_settings
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java)); true
                }

                R.id.nav_fav -> {
                    startActivity(Intent(this, FavoriteActivity::class.java)); true
                }

                R.id.nav_cart -> {
                    startActivity(Intent(this, CartActivity::class.java)); true
                }

                R.id.nav_settings -> true
                else -> false
            }
        }
    }

    // Security Question / PIN dialog for forget password
    private fun showSecurityQuestionDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_security_question, null)
        val etAnswer = view.findViewById<EditText>(R.id.etSecurityAnswer)
        val tvError = view.findViewById<TextView>(R.id.tvSecurityError)

        AlertDialog.Builder(this)
            .setTitle("Security Question")
            .setMessage("What is your favorite color?") // Example question
            .setView(view)
            .setPositiveButton("Submit", null)
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create().apply {
                setOnShowListener {
                    val btnSubmit = getButton(AlertDialog.BUTTON_POSITIVE)
                    btnSubmit.setOnClickListener {
                        tvError.visibility = TextView.GONE
                        val answer = etAnswer.text.toString()

                        if (answer.isBlank()) {
                            tvError.text = "Please answer the question"
                            tvError.visibility = TextView.VISIBLE
                            return@setOnClickListener
                        }

                        // TODO: Verify answer from database
                        // if (!database.verifySecurityAnswer(userId, answer)) { ... }

                        Toast.makeText(
                            context,
                            "Verification passed! You can now reset your password",
                            Toast.LENGTH_SHORT
                        ).show()
                        dismiss()
                    }
                }
                show()
            }
    }
}