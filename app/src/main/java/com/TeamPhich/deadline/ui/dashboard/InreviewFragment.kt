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
import kotlinx.android.synthetic.main.inreview_fragment.*
//nhiem vu trong review sắp hoàn thành
class InreviewFragment :Fragment() {
    //    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
//        inflater.inflate(R.layout.inreview_fragment, container, false)
//
//    companion object {
//        fun newInstance(): InreviewFragment = InreviewFragment()
//    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.inreview_fragment , container, false)
        var arraytask : ArrayList<doneTasks> = ArrayList()
        arraytask.add(doneTasks("diepninh", "3/1/1111"))
        val _listview=view.findViewById<ListView>(R.id.listview_inreviewtasks)
        val context: Context = context!!

        _listview.adapter = CustomAdapter( context,arraytask)

        return view
    }

    companion object {
        fun newInstance():InreviewFragment =InreviewFragment()
    }
}