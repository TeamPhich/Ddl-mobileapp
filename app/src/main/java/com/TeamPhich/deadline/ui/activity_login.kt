package com.TeamPhich.deadline.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.saveToken.SharedPreference
import android.content.Intent
import android.util.Log
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.dashboard

import kotlinx.android.synthetic.main.login_activity.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class activity_login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        val sharedPreference: SharedPreference = SharedPreference(this)
        sup.setOnClickListener {
            val intent =Intent(this,singup_activity::class.java)
            startActivity(intent)
        }
        butSignin.setOnClickListener {
            val username = _iUsername.text.toString();
            val password = _iPassword.text.toString();
            if (username.isEmpty()) {
                _iUsername.error = "Password required"
                _iUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                _iPassword.error = "User Name required"
                _iPassword.requestFocus()
                return@setOnClickListener
            }

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.login(username, password).await()
                    if (response.success == true) {
                        Toast.makeText(applicationContext, "login success", Toast.LENGTH_LONG).show()
                        sharedPreference.resetToken()
                        sharedPreference.setToken(response.data.token)
                        startActivity(Intent(this@activity_login, dashboard::class.java))
                        Log.d("token",response.data.token)
                    } else {
                        Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }


            }




        }


    }




}
