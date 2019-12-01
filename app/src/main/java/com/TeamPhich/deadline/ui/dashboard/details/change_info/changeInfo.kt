package com.TeamPhich.deadline.ui.dashboard.details.change_info

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_change_info.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class changeInfo : AppCompatActivity() {
    var urlimage = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_info)
        val fullname = intent.getStringExtra("fullname")
        val email = intent.getStringExtra("email")
        val avatar = intent.getStringExtra("avatar")

        Glide
            .with(this)
            .load(avatar)
            .centerCrop()
            .placeholder(R.drawable.ic_insert_photo)
            .into(_new_info_avatar);
        _new_info_avatar
        _iNeweMail.setText(email)
        _iNewfullname.setText(fullname)
        urlimage = avatar


//        _exitchange.setOnClickListener {
//            finish()
//        }
//        _bchangepass.setOnClickListener {
//            val new = _iNeweMail.text.toString();
//            if (new.isEmpty()) {
//                _iNeweMail.error = "fullname required"
//                _iNeweMail.requestFocus()
//                return@setOnClickListener
//            }
//
//        }
        setavatar()
        _changeinfo.setOnClickListener {
            val newemail = _iNeweMail.text.toString();
            val newfullname = _iNewfullname.text.toString();
            if (newemail.isEmpty()) {
                _iNeweMail.error = "email required"
                _iNeweMail.requestFocus()
                return@setOnClickListener
            }

            if (newfullname.isEmpty()) {
                _iNewfullname.error = "full name  required"
                _iNewfullname.requestFocus()
                return@setOnClickListener
            }
            val sharedPreference: SharedPreference = SharedPreference(this)
            GlobalScope.launch(Dispatchers.Main) {

                try {
                    val response = RetrofitClient.instance.changeinfo(
                        sharedPreference.getTokenSpace().toString(),
                        newfullname,
                        newemail,
                        urlimage
                    ).await()
                    if (response.success == true) {

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

    fun setavatar() {
        _new_info_avatar.setOnClickListener {

        }
    }
}
