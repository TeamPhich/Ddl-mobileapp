package com.TeamPhich.deadline.ui.dashboard.space_actyvity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.add_a_space.*
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class createSpace_activity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_a_space)

        val sharedPreference: SharedPreference = SharedPreference(this)
        val spacename = _iSpaceName.toString()
        _bCreate.setOnClickListener {
            if (spacename.isEmpty()) {
                _iSpaceName.error = "Space's name required"
                _iSpaceName.requestFocus()
                return@setOnClickListener
            }

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.createSpace(sharedPreference.getToken().toString(),spacename  ).await()
                    if (response.success == true) {
                        Toast.makeText(applicationContext, "OK", Toast.LENGTH_LONG)
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