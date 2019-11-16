package com.TeamPhich.deadline.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.inreview_fragment.*

class InreviewFragment : AppCompatActivity() {
    //    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
//        inflater.inflate(R.layout.inreview_fragment, container, false)
//
//    companion object {
//        fun newInstance(): InreviewFragment = InreviewFragment()
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inreview_fragment)
        var arraytask: ArrayList<doneTasks> = ArrayList()
        arraytask.add(doneTasks("tri tue nhan tao", "20/11/2019"))
        listview_inreviewtasks.adapter = CustomAdapter(this, arraytask)
    }
}