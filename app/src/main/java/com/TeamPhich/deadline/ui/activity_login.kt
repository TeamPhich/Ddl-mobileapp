package com.TeamPhich.deadline.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.saveToken.SharedPreference

import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main._iUserName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class activity_login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreference: SharedPreference = SharedPreference(this)
        butSignin.setOnClickListener {
            val username = _iUserName.text.toString();
            val password = _iPassword.text.toString();
            if (password.isEmpty()) {
                _iPassWord.error = "Password required"
                _iPassWord.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                _iUserName.error = "User Name required"
                _iUserName.requestFocus()
                return@setOnClickListener
            }

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.login(username, password).await()
                    if (response.success == true) {
                        Toast.makeText(applicationContext, "login success", Toast.LENGTH_LONG)
                            .show()
                        sharedPreference.resetToken()
                        sharedPreference.setToken(response.data.token)
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