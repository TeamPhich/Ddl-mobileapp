package com.TeamPhich.deadline.ui.dashboard.details

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.R.id._fullnameinfo
import com.TeamPhich.deadline.responses.Space.userprofile.Profile
import com.TeamPhich.deadline.responses.Space.userprofile.userProfile
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.dashboard
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.information.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Information : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information)
        // an close de dong information
        _closeactivity.setOnClickListener {
            finish()
        }
        _changepass.setOnClickListener {
            val intent = Intent(this,changepassword::class.java)
            startActivity(intent)
        }
        _fullnameinfo.setOnClickListener {
            Log.d("diepxinhgai","vl")
        }
        getinfo()

    }
    fun getinfo(){

        val sharedPreference: SharedPreference = SharedPreference(this)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.instance.getuserprofile(sharedPreference.getTokenSpace().toString()).await()
                if (response.success == true) {
                    Toast.makeText(applicationContext, "lo", Toast.LENGTH_LONG).show()

                    setinfo(response.data)
                    Log.d("uy485t845",response.toString())
                } else {
                    Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }


        }
    }
    fun setinfo( userProfile: userProfile){

        userProfile.profile.forEach {
            Glide
                .with(this)
                .load(it.imagesUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_insert_photo)
                .into(_info_avata);
            _info_avata
            _info_fullname.text=it.fullName
            _info_role.text=it.roleName
        }

    }
}
