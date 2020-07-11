package com.example.onlineshopping.menu_fragments


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.onlineshopping.R
import com.example.onlineshopping.activities.MainActivity
import com.example.onlineshopping.adapter.ProductAdapter
import com.example.onlineshopping.database.DatabaseHelperProduct
import com.example.onlineshopping.model.Product
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val root =inflater.inflate(R.layout.fragment_home, container, false)

        val db = DatabaseHelperProduct(context!!)

        val data = db.getAllProducts()


        root.home_fragment_show_all_product.layoutManager = LinearLayoutManager(context)
        //rvStudents.layoutManager = GridLayoutManager(this,2)



        root.home_fragment_show_all_product.setHasFixedSize(true)

        val productAdapter = ProductAdapter(context!!, data)
        root.home_fragment_show_all_product.adapter = productAdapter


        return root
    }


}
