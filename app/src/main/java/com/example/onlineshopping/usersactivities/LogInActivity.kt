package com.example.onlineshopping.usersactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.onlineshopping.R
import com.example.onlineshopping.activities.MainActivity
import com.example.onlineshopping.database.DatabaseHelperUser
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val db = DatabaseHelperUser(this)

        log_in_button.setOnClickListener {
            val userName = log_in_user_name.text.toString()
            val passText = log_in_password.text.toString()

            if(db.checkUser(userName , passText)){

                val preferences = getSharedPreferences("myprefs", MODE_PRIVATE)
                val editor = preferences.edit()

                editor.putBoolean("logged_in", true)
                editor.putString("token", userName)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this , "you do not have an account , please sign up " , Toast.LENGTH_LONG).show()
            }

        }

        log_in_register_button.setOnClickListener(){
            startActivity(Intent(this , SingUpActivity::class.java))
        }


    }
}
