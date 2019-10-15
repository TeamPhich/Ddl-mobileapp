package com.TeamPhich.deadline.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.defaultRespone
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textLogin.setOnClickListener{
            startActivity(Intent(this@MainActivity, activity_login::class.java))
        }
        ButSignup.setOnClickListener {

            val email = _iEmail.text.toString().trim()
            val password = _iPassWord.text.toString().trim()
            val username = _iUserName.text.toString().trim()



            if(email.isEmpty()){
                _iEmail.error = "Email required"
                _iEmail.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                _iPassWord.error = "Password required"
                _iPassWord.requestFocus()
                return@setOnClickListener
            }

            if(username.isEmpty()){
                _iUserName.error = "User Name required"
                _iUserName.requestFocus()
                return@setOnClickListener
            }



            RetrofitClient.instance.createUser(username, password, email)
                .enqueue(object: Callback<defaultRespone> {
                    override fun onFailure(call: Call<defaultRespone>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<defaultRespone>, response: Response<defaultRespone>) {

                        if(response.body()?.success==true){
                            Toast.makeText(applicationContext,"sign up succes",Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(applicationContext, response.body()?.reason, Toast.LENGTH_LONG).show()
                        }
                    }

                })

        }

    }

}
