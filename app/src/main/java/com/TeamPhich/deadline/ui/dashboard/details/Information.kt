package com.TeamPhich.deadline.ui.dashboard.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.userprofile.userProfile
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.details.change_info.changeInfo
import com.TeamPhich.deadline.ui.dashboard.details.change_info.changepassword
import com.bumptech.glide.Glide
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
            val intent = Intent(this,
                changepassword::class.java)
            startActivity(intent)
        }
        _fullnameinfo.setOnClickListener {

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


            Glide
                .with(this)
                .load(userProfile.profile.get(0).imagesUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_insert_photo)
                .into(_new_info_avatar);
            _new_info_avatar
            _info_fullname.text=userProfile.profile.get(0).fullName
            _info_role.text=userProfile.profile.get(0).roleName
            _info_nameuser.text=userProfile.profile.get(0).userName


    }


    fun callnewActivity(userProfile:userProfile){

        val intent =Intent(this,changeInfo::class.java)
        intent.putExtra("avatar",userProfile.profile.get(0).imagesUrl) //Put your id to your next Intent
        intent.putExtra("email",userProfile.profile.get(0).email)
        intent.putExtra("fullname",userProfile.profile.get(0).fullName)
        startActivity(intent)
    }
}
