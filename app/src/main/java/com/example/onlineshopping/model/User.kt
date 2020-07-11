package com.example.onlineshopping.model

import android.os.Parcel
import android.os.Parcelable

data class  User (var id:Int , var username:String? ,var name:String? ,
                  var email:String? ,var phone:String?, var password:String? ,var Image:String?): Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()

    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(password)
        parcel.writeString(Image)


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User{
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
        val COL_ID = "id"
        val COL_USERNAME = "username"
        val COL_NAME = "name"
        val COL_EMAIL = "email"
        val COL_PHONE = "phone"
        val COL_PASSWORD = "password"
        val COL_IMAGE = "image"
        val TABLE_NAME = "Users"
        val TABLE_CREATE = "create table $TABLE_NAME ($COL_ID integer primary key autoincrement, $COL_USERNAME text not null , $COL_NAME text not null, $COL_EMAIL text not null, $COL_PHONE text not null ,$COL_PASSWORD text not null , $COL_IMAGE text)"

    }


}


