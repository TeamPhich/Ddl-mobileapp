package com.TeamPhich.deadline.ui.dashboard.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.changepass.*

class changepassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changepass)
        _exitchange.setOnClickListener {
            finish()
        }
        _cancelchange.setOnClickListener {
            finish()
        }
    }
}
