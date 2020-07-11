package com.example.onlineshopping.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopping.R
import com.example.onlineshopping.adapter.ProductAdapter
import com.example.onlineshopping.database.DatabaseHelperProduct
import com.example.onlineshopping.database.DatabaseHelperUser
import com.example.onlineshopping.model.User
import kotlinx.android.synthetic.main.activity_profile.*




class profileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPref = getSharedPreferences("myprefs", MODE_PRIVATE)
        val username = sharedPref.getString("token","")

        profile_user_name_show.setText(username)

        val db = DatabaseHelperProduct(this)
        val data = db.getUserProduct(username!!)

        profile_my_product_recycler_view.layoutManager = LinearLayoutManager(this)
        //rvStudents.layoutManager = GridLayoutManager(this,2)

        profile_my_product_recycler_view.setHasFixedSize(true)

        val productAdapter = ProductAdapter(this, data)
        profile_my_product_recycler_view.adapter = productAdapter


    }
}
