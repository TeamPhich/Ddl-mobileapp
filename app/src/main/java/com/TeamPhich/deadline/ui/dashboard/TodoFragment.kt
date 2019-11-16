package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.todo_fragment.*

class TodoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,  container: ViewGroup?,  savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.todo_fragment, container, false)
        var arraytask : ArrayList<doneTasks> = ArrayList()
        arraytask.add(doneTasks("diepninh", "1/1/1111"))
        val _listview=view.findViewById<ListView>(R.id._listview_todotasks)
        val context: Context = context!!

        _listview.adapter = CustomAdapter( context,arraytask)

        return view
    }

    companion object {
        fun newInstance(): TodoFragment = TodoFragment()
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.todo_fragment)
//        var arraytask : ArrayList<doneTasks> = ArrayList()
//        //du lieu cua listviewtodo
//        arraytask.add(doneTasks("con cho ","1/1/1111"))
//        listview_todotasks.adapter = CustomAdapter(this,arraytask)
//
//    }
}