package com.example.onlineshopping.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.onlineshopping.R
import com.example.onlineshopping.database.DatabaseHelperProduct
import com.example.onlineshopping.model.Product
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.fragment_add_product.view.*

class AddProductActivity : AppCompatActivity() {



    var imageURI:String = ""
    var catogrey:String="Clothes"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)


        val db = DatabaseHelperProduct(this)

        add_product_image_view.setOnClickListener {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),100)
                    return@setOnClickListener
                }else{
                    val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(i, 100)
                }
            }
        }


        val sharedPref = getSharedPreferences("myprefs", MODE_PRIVATE)
        val username = sharedPref.getString("token","")

        val languages = resources.getStringArray(R.array.Categories)

        // access the spinner
        var category = ""
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    category=languages[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }



        add_product_button.setOnClickListener {
            //price: Double ,title:String ,category:String ,userName:String , image:String
            if ( add_product_price.text!!.isNotEmpty() && add_product_title.text!!.isNotEmpty()
                && category.isNotEmpty()&& username!!.isNotEmpty()){

                if (db.insertProuduct(add_product_price.text.toString().toDouble(),add_product_title.text.toString(),
                        category,username,imageURI)){


                    Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "Add Failed", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Fill Fields", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode==100){
            imageURI = data!!.data.toString()
            Log.e("hzm",imageURI)
            add_product_image_view.setImageURI(data.data)
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
