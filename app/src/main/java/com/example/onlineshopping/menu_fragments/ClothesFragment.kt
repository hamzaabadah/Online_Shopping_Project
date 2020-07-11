package com.example.onlineshopping.menu_fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.onlineshopping.R
import com.example.onlineshopping.adapter.ProductAdapter
import com.example.onlineshopping.database.DatabaseHelperProduct
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_clothes.view.*

/**
 * A simple [Fragment] subclass.
 */
class ClothesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val root = inflater.inflate(R.layout.fragment_clothes, container, false)

        val db = DatabaseHelperProduct(context!!)
        val data = db.getCatogreyOfProuduct("Clothes")


        root.clothes_rec.layoutManager = LinearLayoutManager(context)
        //rvStudents.layoutManager = GridLayoutManager(this,2)

        root.clothes_rec.setHasFixedSize(true)

        val productAdapter = ProductAdapter(context!!, data)
        root.clothes_rec.adapter = productAdapter


        return root
    }


}
