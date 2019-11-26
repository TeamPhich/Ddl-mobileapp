package com.TeamPhich.deadline.ui.dashboard.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.R.id._fullnameinfo
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.information.*

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

    }
}
