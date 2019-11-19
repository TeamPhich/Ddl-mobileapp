package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.update_task.*
import kotlinx.android.synthetic.main.update_task.view.*

class UpdatetaskFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.update_task, container, false)
         view._bupdate.setOnClickListener { view ->
            Log.d("diep","123")
        }

        return view
    }

    companion object {
        fun newInstance(): UpdatetaskFragment = UpdatetaskFragment()
    }
}