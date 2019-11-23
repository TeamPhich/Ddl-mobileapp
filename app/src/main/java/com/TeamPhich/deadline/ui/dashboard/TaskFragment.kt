package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.ui.dashboard.custom_adapter.CustomAdapter_listviewtasks
import com.TeamPhich.deadline.ui.dashboard.task.newTask
import kotlinx.android.synthetic.main.tablayout.view.*

// nhiemvu duoc giao hoan thanh
class TaskFragment(var status: String) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.task_fragment, container, false)
        var arraytask : ArrayList<doneTasks> = ArrayList()
        when(status) {
            "todo" -> {
            }
            "in process" -> {
            }
            "in review" -> {
            }
            "done" -> {
            }
        }
        if(status == "done"){
            arraytask.add(doneTasks("diepninh4", "4/1/1111" , R.drawable.pikachu,"diepxinhgaiaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
        }
        val _listview=view.findViewById<ListView>(R.id.listview_donetasks )
        val context: Context = context!!

        _listview.adapter =
            CustomAdapter_listviewtasks(
                context,
                arraytask,
                status
            )

        return view
    }

    companion object {
        fun newInstance(status: String):TaskFragment =TaskFragment(status)
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        var view : View? =inflater.inflate(task_fragment, container, false)
//        return view
//    }


//    companion object {
//        fun newInstance(): TaskFragment = TaskFragment()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
    fun addtask(){
    val liststatus=mutableListOf("todo")

}

}