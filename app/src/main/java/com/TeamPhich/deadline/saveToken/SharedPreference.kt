package com.TeamPhich.deadline.saveToken

import android.content.Context

class SharedPreference(val cont: Context) {

    val SharedPreferences = cont.getSharedPreferences("storeToken", Context.MODE_PRIVATE)
    fun save(key: String, value: String) {
        val editor = SharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun setToken(value: String?) {
        val editor = SharedPreferences.edit()
        editor.putString("token", value)
        editor.commit()
    }
    fun setSpaceName(value: String?) {
        val editor = SharedPreferences.edit()
        editor.putString("name", value)
        editor.commit()
    }
    fun getToken(): String? {
        return SharedPreferences.getString("token", "")
    }
    fun getSpaceName(): String? {
        return SharedPreferences.getString("name", "")
    }
    fun resetToken() {
        val editor = SharedPreferences.edit()
        editor.remove("token")
    }

    fun setTokenSpace(value: String?) {
        val editor = SharedPreferences.edit()
        editor.putString("tokenSpace", value)
        editor.commit()
    }

    fun getTokenSpace(): String? {
        return SharedPreferences.getString("tokenSpace", "")
    }

    fun resetTokenSpace() {
        val editor = SharedPreferences.edit()
        editor.remove("tokenSpace")
    }


    fun setRole(value: String?) {
        val editor = SharedPreferences.edit()
        editor.putString("Role", value)
        editor.commit()
    }

    fun getRole(): String? {
        return SharedPreferences.getString("Role", "")
    }

    fun resetRole() {
        val editor = SharedPreferences.edit()
        editor.remove("Role")
    }

}