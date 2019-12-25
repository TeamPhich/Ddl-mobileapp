package com.TeamPhich.deadline.ui.dashboard.details.change_info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.dashboard
import kotlinx.android.synthetic.main.changepass.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class changepassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changepass)
        _exitchagepass.setOnClickListener { finish() }
        _bchangepass.setOnClickListener {
            val oldpass=_ioldpass.toString().trim()
            val newpass=_inewpass.toString().trim()
            val confirm=_icfnewpass.toString().trim()
            if (oldpass.isEmpty()) {
                _ioldpass.error = "Email required"
                _ioldpass.requestFocus()
                return@setOnClickListener
            }
            if (newpass.isEmpty()) {
                _inewpass.error = "Email required"
                _inewpass.requestFocus()
                return@setOnClickListener
            }
            if (confirm.isEmpty()) {
                _icfnewpass.error = "Email required"
                _icfnewpass.requestFocus()
                return@setOnClickListener
            }
            if(confirm!=newpass){
                _inewpass.text.clear()
                _icfnewpass.text.clear()
                _inewpass.error = "confirm password khong giong password"
                return@setOnClickListener
            }
            if(confirm==oldpass){
                _inewpass.text.clear()
                _icfnewpass.text.clear()
                _inewpass.error = "old pass phai khac newpass"
                return@setOnClickListener
            }
            val sharedPreference: SharedPreference = SharedPreference(this)
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.changepass(sharedPreference.getToken().toString(), oldpass,newpass).await()
                    if (response.success == true) {
                        Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }


            }
        }

//        _.setOnClickListener {
//            finish()
//        }

    }
}
