package com.example.onlineshopping.database

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.onlineshopping.model.Product
import com.example.onlineshopping.model.User

class DatabaseHelperProduct(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    private val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Product.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop table if exists ${Product.TABLE_NAME}")
        onCreate(db)
    }

    //var price:Double, var title: String?, var category: String?,var userName: String?, var image:String?




    fun insertProuduct( price: Double ,title:String ,category:String? ,userName:String?,image:String ): Boolean {
        val cv = ContentValues()
        cv.put(Product.COL_PRICE, price)
        cv.put(Product.COL_TITLE, title)
        cv.put(Product.COL_CATEGORY, category)
        cv.put(Product.COL_USERNAME, userName)
        cv.put(Product.COL_IMAGE, image)
        return db.insert(Product.TABLE_NAME, null, cv) > 0

    }

    fun getAllProducts(): ArrayList<Product> {
        val data = ArrayList<Product>()
        val c =
            db.rawQuery("select * from ${Product.TABLE_NAME} order by ${Product.COL_ID} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Product(c.getInt(0),c.getDouble(1), c.getString(2),
                c.getString(3),  c.getString(4),c.getString(5))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }

    fun getCatogreyOfProuduct(catogrey:String):ArrayList<Product>{

        val data = ArrayList<Product>()

        val c =
            db.rawQuery("select * from ${Product.TABLE_NAME} where ${Product.COL_CATEGORY} = '$catogrey'",null)
        c.moveToFirst()
        c.count
        while (!c.isAfterLast) {
            objectProduct(c,data)
        }
        c.close()
        return data
    }

    fun getUserProduct(username:String):ArrayList<Product>{
        val data = ArrayList<Product>()

        val c =
            db.rawQuery("select * from ${Product.TABLE_NAME} where ${Product.COL_USERNAME} = '$username'",null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            objectProduct(c,data)
        }
        c.close()
        return data
    }

    fun objectProduct(cursor: Cursor , data:ArrayList<Product>){
        val s = Product(cursor.getInt(0),cursor.getDouble(1), cursor.getString(2),
            cursor.getString(3),  cursor.getString(4),cursor.getString(5))
        data.add(s)
        cursor.moveToNext()
    }

    companion object {
        val DATABASE_NAME = "PRODUCTS"
        val DATABASE_VERSION = 3
    }

}