package com.TeamPhich.deadline.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.defaultRespone
import com.TeamPhich.deadline.saveToken.SharedPreference

import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main._iUserName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class activity_login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreference:SharedPreference=SharedPreference(this)
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
            RetrofitClient.instance.login(username, password )
                .enqueue(object: Callback<defaultRespone> {
                    override fun onFailure(call: Call<defaultRespone>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<defaultRespone>, response: Response<defaultRespone>) {

                        if(response.body()?.success==true){
                            Toast.makeText(applicationContext,"sign in success", Toast.LENGTH_LONG).show()
                            sharedPreference.resetToken()
                            sharedPreference.setToken(response.body()?.data?.token)
                            Log.d("thangdznhat",sharedPreference.getToken())


                        }
                        else{
                            Toast.makeText(applicationContext, response.body()?.reason, Toast.LENGTH_LONG).show()
                        }
                    }

                })

        }

    }







}
