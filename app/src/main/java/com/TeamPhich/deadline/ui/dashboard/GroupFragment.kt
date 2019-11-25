package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.ui.dashboard.custom_adapter.CustomAdapter_listviewgroup
import com.TeamPhich.deadline.ui.dashboard.task.Group

class GroupFragment :Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.group , container, false)
        var arraygroup : ArrayList<Group> = ArrayList()
        arraygroup.add(Group("thang, si ,diep",R.drawable.husky))

        val _listview=view.findViewById<ListView>(R.id.listview_group )
        val context: Context = context!!

        _listview.adapter =
            CustomAdapter_listviewgroup(
                context,
                arraygroup
            )
        //bắt sự kiện cho listview trong group
        _listview.setOnItemClickListener { parent, view, position, id ->
            if (position == 0)
            {
                Toast.makeText(context,"move chat",Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }

    companion object {
        fun newInstance():GroupFragment = GroupFragment()
    }
}