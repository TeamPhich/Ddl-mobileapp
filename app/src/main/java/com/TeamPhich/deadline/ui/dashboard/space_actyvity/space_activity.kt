package com.TeamPhich.deadline.ui.dashboard.space_actyvity

import android.content.Context
import android.widget.Toast
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.TeamPhich.deadline.ui.dashboard.dashboard


class space_activity{
    val cont: Context get() {
            TODO()
        }
    val sharedPreference: SharedPreference = SharedPreference(cont)

    fun getlistMember(){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getSpacemember(sharedPreference.getTokenSpace().toString()).await()

                if (response.success == true) {



                } else {

                }
            } catch (t: Throwable) {

            }


        }
    }
}
