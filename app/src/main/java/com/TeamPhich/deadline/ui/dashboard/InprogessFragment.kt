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
import kotlinx.android.synthetic.main.inprogess_fragment.*
//nhiem vu dang trong tien trinh làm việc được cập nhật ở trạng thái này sau đó là inreview
class InprogessFragment : Fragment() {
    //    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
//        inflater.inflate(R.layout.inprogess_fragment, container, false)
//
//    companion object {
//        fun newInstance(): InprogessFragment = InprogessFragment()
//    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.inprogess_fragment , container, false)
        var arraytask : ArrayList<doneTasks> = ArrayList()
        arraytask.add(doneTasks("diepninh", "2/1/1111"))
        val _listview=view.findViewById<ListView>(R.id.listview_inprogesstasks)
        val context: Context = context!!

        _listview.adapter = CustomAdapter( context,arraytask)

        return view
    }

    companion object {
        fun newInstance():InprogessFragment =InprogessFragment()
    }
}