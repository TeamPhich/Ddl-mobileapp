package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.people_fragment.view.*

class GroupFragment :Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.group , container, false)
        var arraypeople : ArrayList<Group> = ArrayList()
        arraypeople.add(Group("diepninh", R.drawable.pikachu))
        val _listview=view.findViewById<ListView>(R.id.listview_group )
        val context: Context = context!!

        _listview.adapter = CustomAdapter_listviewgroup( context,arraypeople)
        //bắt sự kiện cho listview trong group
        _listview.setOnItemClickListener { parent, view, position, id ->
            if (position == 0)
            {
                Toast.makeText(context,"move chat",Toast.LENGTH_SHORT).show()
            }
        }
        //bắt sự kiện cho nút add member
        view._addmember.setOnClickListener { view ->
            Log.d("diepdẽxuong","123")
        }

        return view
    }

    companion object {
        fun newInstance():GroupFragment = GroupFragment()
    }
}