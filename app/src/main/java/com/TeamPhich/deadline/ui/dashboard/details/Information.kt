package com.TeamPhich.deadline.ui.dashboard.details

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.userprofile.userProfile
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.activity_login
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


        getinfo()

        _logout.setOnClickListener {
            calldialog_logout()
        }
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
            callnewActivity(userProfile)

    }


    fun callnewActivity(userProfile:userProfile){
        _changeyourinfo.setOnClickListener {


        val intent =Intent(this,changeInfo::class.java)
        intent.putExtra("avatar",userProfile.profile.get(0).imagesUrl) //Put your id to your next Intent
        intent.putExtra("email",userProfile.profile.get(0).email)
        intent.putExtra("fullname",userProfile.profile.get(0).fullName)
            startActivityForResult(intent, 1)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getinfo()
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun calldialog_logout(){
        val builder = AlertDialog.Builder(this)
        val sharedPreference: SharedPreference = SharedPreference(this)
        // Set the alert dialog title
        builder.setTitle("App background color")

        builder.setMessage("Ban muon xoa task task sang trang thai")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("YES") { dialog, which ->
            sharedPreference.resetToken()
            sharedPreference.resetTokenSpace()
            val intent = Intent(this, activity_login::class.java)
            startActivity(intent)
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(this, "You are not agree.", Toast.LENGTH_SHORT).show()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
