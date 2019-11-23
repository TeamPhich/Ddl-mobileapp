package com.TeamPhich.deadline.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.information.*

class Information : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information)
        // an close de dong information
        _closeactivity.setOnClickListener {
            finish()
        }
    }
}
