package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.task.task
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.custom_adapter.CustomAdapter_listviewtasks
import com.TeamPhich.deadline.ui.dashboard.task.newTask
import kotlinx.android.synthetic.main.tablayout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// nhiemvu duoc giao hoan thanh
class TaskFragment(var status: String) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.task_fragment, container, false)
        var arraytask : ArrayList<task> = ArrayList()
        when(status) {
            "todo" -> {
                getlist(status,arraytask)
            }
            "in process" -> {
                getlist(status,arraytask)
            }
            "in review" -> {
                getlist(status,arraytask)
            }
            "done" -> {
                getlist(status,arraytask)
            }
        }

//            arraytask.add(doneTasks("diepninh4", "4/1/1111" , R.drawable.pikachu,"diepxinhgaiaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))

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
    fun getlist(status: String,arraytask:ArrayList<task>){
        val sharedPreference: SharedPreference = SharedPreference(requireContext())
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.instance.showSpacetask(sharedPreference.getTokenSpace().toString(), status).await()
                if (response.success == true) {
                    response.data.rows.forEach {
                        arraytask.add(it)
                    }

                } else {
                    Toast.makeText(requireContext(), response.reason, Toast.LENGTH_LONG)
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
            }


        }

    }
}