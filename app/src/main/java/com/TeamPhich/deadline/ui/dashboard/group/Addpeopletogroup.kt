package com.TeamPhich.deadline.ui.dashboard.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.ui.dashboard.custom_adapter.itemPeopleinSpace
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_addpeopletogroup.*
import kotlinx.android.synthetic.main.people_fragment.view.*

class Addpeopletogroup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addpeopletogroup)
        var adapter = GroupAdapter<ViewHolder>()

            adapter.add(people())


        _reyclerview_list_peopleinspace2.adapter=adapter
    }
}
