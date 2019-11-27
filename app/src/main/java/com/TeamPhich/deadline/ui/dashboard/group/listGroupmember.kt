package com.TeamPhich.deadline.ui.dashboard.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.TeamPhich.deadline.R

class listGroupmember : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_groupmember)
        val group_name=intent.getStringExtra("group_name")
        val group_id=intent.getIntExtra("group_id",0)

    }
}
