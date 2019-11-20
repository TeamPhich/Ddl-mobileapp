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
import kotlinx.android.synthetic.main.done_fragment.*
// nhiemvu duoc giao hoan thanh
class DoneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.done_fragment , container, false)
        var arraytask : ArrayList<doneTasks> = ArrayList()
        arraytask.add(doneTasks("diepninh", "4/1/1111"))
        val _listview=view.findViewById<ListView>(R.id.listview_donetasks )
        val context: Context = context!!

        _listview.adapter = CustomAdapter( context,arraytask)

        return view
    }

    companion object {
        fun newInstance():DoneFragment =DoneFragment()
    }
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        var view : View? =inflater.inflate(done_fragment, container, false)
//        return view
//    }


//    companion object {
//        fun newInstance(): DoneFragment = DoneFragment()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

}