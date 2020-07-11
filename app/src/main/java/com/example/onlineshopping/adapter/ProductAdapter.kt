package com.example.onlineshopping.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopping.R
import com.example.onlineshopping.model.Product
import kotlinx.android.synthetic.main.row_all_product.view.*

class ProductAdapter(var context: Context, var data: ArrayList<Product>) :

    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // test
        val root = LayoutInflater.from(parent.context).inflate(R.layout.row_all_product, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.row_all_product_title.text = data[position].title
        holder.row_all_product_price.text = data[position].price.toString()
        holder.row_all_product_category.text = data[position].category
        holder.row_all_product_user_name.text = data[position].userName
        holder.row_all_product_image_view.setImageURI(Uri.parse(data[position].image))

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val row_all_product_title = itemView.row_all_product_title
        val row_all_product_price= itemView.row_all_product_price
        val row_all_product_category=itemView.row_all_product_category
        val row_all_product_user_name=itemView.row_all_product_user_name
        val row_all_product_image_view=itemView.row_all_product_image_view
    }

}