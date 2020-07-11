package com.example.onlineshopping.usersactivities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.onlineshopping.R
import com.example.onlineshopping.activities.MainActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 3000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            // Check if user logged in before or not
            val preferences = getSharedPreferences("myprefs", MODE_PRIVATE)
            val isLoggedIn = preferences.getBoolean("logged_in", false)

            if (isLoggedIn) {
                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(mainIntent)
                finish()

            } else {
                val mainIntent = Intent(this@SplashActivity, LogInActivity::class.java)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(mainIntent)
                finish()
            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
