package com.example.onlineshopping.model

import android.os.Parcel
import android.os.Parcelable

data class Product(var id:Int ,var price:Double, var title: String?, var category: String?,var userName: String?, var image:String?) :
    Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeDouble(price)
        parcel.writeString(title)
        parcel.writeString(category)
        parcel.writeString(userName)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }

        val CLOTHES = "Clothes"

        val COL_ID= "id"
        val COL_PRICE = "price"
        val COL_TITLE = "title"
        val COL_CATEGORY = "category"
        val COL_USERNAME = "userName"
        val COL_IMAGE = "image"

        val TABLE_NAME = "PRODUCT"

        val TABLE_CREATE = "create table $TABLE_NAME ($COL_ID integer primary key autoincrement," +
                "$COL_PRICE DOUBLE,$COL_TITLE text not null, $COL_CATEGORY text not null,$COL_USERNAME text not null, $COL_IMAGE text)"
    }




}