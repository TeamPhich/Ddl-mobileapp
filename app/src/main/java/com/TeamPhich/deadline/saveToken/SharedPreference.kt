package com.TeamPhich.deadline.saveToken

import android.content.Context

class SharedPreference( val cont: Context)  {

        val SharedPreferences = cont.getSharedPreferences("storeToken", Context.MODE_PRIVATE)
        fun save( key: String,value: String) {
            val editor = SharedPreferences.edit()
            editor.putString(key, value)
            editor.commit()
        }
        fun setToken( value: String?) {
        val editor = SharedPreferences.edit()
            editor.putString("token", value)
            editor.commit()
        }
        fun getToken():String?{
            return SharedPreferences.getString("token","")
        }
        fun resetToken(){
            val editor = SharedPreferences.edit()
            editor.remove("token")
        }

}