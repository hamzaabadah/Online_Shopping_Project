package com.example.onlineshopping.usersactivities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.onlineshopping.R
import com.example.onlineshopping.activities.MainActivity
import com.example.onlineshopping.database.DatabaseHelperProduct
import com.example.onlineshopping.database.DatabaseHelperUser
import kotlinx.android.synthetic.main.activity_sing_up.*

class SingUpActivity : AppCompatActivity() {

    var imageURI:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        val db = DatabaseHelperUser(this)

        sing_up_image_view.setOnClickListener {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),100)
                    return@setOnClickListener
                }else{
                    val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(i, 100)
                }
            }
        }

        sing_up_button.setOnClickListener {

            if (sing_up_user_name.text!!.isNotEmpty() && sing_up_complete_name.text!!.isNotEmpty()&&
                sing_up_email.text!!.isNotEmpty()&& sing_up_phone_number.text!!.isNotEmpty()&&
                sing_up_password.text!!.isNotEmpty()&& sing_up_confirm_password.text!!.isNotEmpty()) {

                if ( sing_up_password.text.toString() == sing_up_confirm_password.text.toString()){

                    if (db.insertUser(sing_up_user_name.text.toString() , sing_up_complete_name.text.toString() ,
                            sing_up_email.text.toString() , sing_up_phone_number.text.toString() ,
                            sing_up_password.text.toString() , imageURI)) {

                        val preferences = getSharedPreferences("myprefs", MODE_PRIVATE)
                        val editor = preferences.edit()

                        var userName = sing_up_user_name.text.toString()

                        editor.putBoolean("logged_in", true)
                        editor.putString("token", userName)
                        editor.apply()

                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, "Add Failed", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "password doesnt Match", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "All Fields Are Required", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode==100){
            imageURI = data!!.data.toString()
            Log.e("hzm",imageURI)
            sing_up_image_view.setImageURI(data.data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            100 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(i, 100)
                    // permission was granted, yay! do the
                } else {
                    // permission denied, boo! Disable the function
                    finish()
                }
                return
            }
        }
    }


}
