package com.example.onlineshopping.profile_fragments


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.example.onlineshopping.R
import com.example.onlineshopping.database.DatabaseHelperProduct
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.fragment_add_product.view.*

/**
 * A simple [Fragment] subclass.
 */
class AddProductFragment : Fragment() {

    var imageURI:String = ""


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragmentt
        val root = inflater.inflate(R.layout.fragment_add_product, container, false)


        val db = DatabaseHelperProduct(activity!!)


        root.add_product_image_view.setOnClickListener {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),100)
                    return@setOnClickListener
                }else{
                    val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(i, 100)
                }
            }
        }


//price: Double ,title:String ,category:String ,userName:String , image:String

        add_product_button.setOnClickListener {
            //price: Double ,title:String ,category:String ,userName:String , image:String
            if ( root.add_product_price.text!!.isNotEmpty() && root.add_product_title.text!!.isNotEmpty()
                && root.add_product_category.text!!.isNotEmpty() && root.add_product_user_name.text!!.isNotEmpty() ){

                if (db.insertProuduct(root.add_product_price.text.toString().toDouble(),root.add_product_title.text.toString(),
                        root.add_product_category.text.toString(),root.add_product_user_name.text.toString(),imageURI)){


                    Toast.makeText(activity, "Added Successfully", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(activity, "Add Failed", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(activity, "Fill Fields", Toast.LENGTH_SHORT).show()
            }
        }


        /*
        root.add_product_button.setOnClickListener {
            //price: Double ,title:String ,category:String ,userName:String , image:String
            if (root.add_product_price.text.isNullOrEmpty() && root.add_product_title.text.isNullOrEmpty()
                && root.add_product_category.text.isNullOrEmpty() && root.add_product_user_name.text.isNullOrEmpty()){

                if (db.insertProuduct(root.add_product_price.text.toString(),root.add_product_title.text.toString(),
                        root.add_product_category.text.toString(),root.add_product_user_name.text.toString())){


                    Toast.makeText(activity, "Added Successfully", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(activity, "Add Failed", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(activity, "Fill Fields", Toast.LENGTH_SHORT).show()
            }
        }
        */


        return root
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
                    Toast.makeText(activity,"failed",Toast.LENGTH_LONG).show()
                }
                return
            }
        }

    }


}
