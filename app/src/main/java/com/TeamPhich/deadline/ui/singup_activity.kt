package com.TeamPhich.deadline.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.os.Handler

class singup_activity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            System.exit(-1)

            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textLogin.setOnClickListener {
            startActivity(Intent(this@singup_activity, activity_login::class.java))
        }
        ButSignup.setOnClickListener {

            val email = _iEmail.text.toString().trim()
            val password = _iPassWord.text.toString().trim()
            val username = _iUserName.text.toString().trim()



            if (email.isEmpty()) {
                _iEmail.error = "Email required"
                _iEmail.requestFocus()
                return@setOnClickListener
            }


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
                    val response =
                        RetrofitClient.instance.createUser(username, password, email, "ninh thang").await()
                    if (response.success == true) {
                        Toast.makeText(applicationContext, "sign up success", Toast.LENGTH_LONG)
                            .show()
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
