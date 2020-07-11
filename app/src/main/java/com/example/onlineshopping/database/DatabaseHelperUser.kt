package com.example.onlineshopping.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.onlineshopping.model.User

class DatabaseHelperUser(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME_USERS,
    null, DATABASE_VERSION_USERS){

    private val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(User.TABLE_CREATE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop table if exists ${User.TABLE_NAME}")
        onCreate(db)
    }

    fun insertUser( username: String,name: String, email: String ,phone: String,password: String, image:String): Boolean {
        val cv = ContentValues()
        cv.put(User.COL_USERNAME, username)
        cv.put(User.COL_NAME, name)
        cv.put(User.COL_EMAIL, email)
        cv.put(User.COL_PHONE, phone)
        cv.put(User.COL_PASSWORD, password)
        cv.put(User.COL_IMAGE, image)
        return db.insert(User.TABLE_NAME, null, cv) > 0
    }

    fun checkUser(username: String, password: String): Boolean {

        val selectQuery = "SELECT  * FROM ${User.TABLE_NAME} WHERE ${User.COL_USERNAME} = ? AND ${User.COL_PASSWORD} = ?"
        val arguments = arrayOf(username , password)
        val cursor = db.rawQuery(selectQuery , arguments)

        val cursorCount = cursor.count
        Log.d("hzm" , "cursorCounter = "+cursorCount)
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true

        return false

    }

    fun getAllUser(): ArrayList<User> {
        val data = ArrayList<User>()
        val c =
            db.rawQuery("select * from ${User.TABLE_NAME} order by ${User.COL_ID} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = User(c.getInt(0), c.getString(1),
                c.getString(2), c.getString(3),c.getString(4),
                c.getString(5),c.getString(6))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }


    /*
    fun getUserInfo(username:String): Cursor? {


        val c = db.rawQuery("select * from ${User.TABLE_NAME} where ${User.COL_USERNAME} = '$username'",null)
        if(c.getCount()>=1) {
            c.moveToFirst();
            for (i in 0 until c.getCount()) {
            val number = c.getString(0)
            val message = c.getString(1)
            numb += number
            mess += message
            DisplayText(numb, mess)
            count++
        }
        count++
        cc.moveToNext()
        return c
    }
     */





    companion object {
        val DATABASE_NAME_USERS = "USERS"
        val DATABASE_VERSION_USERS = 2
    }

}