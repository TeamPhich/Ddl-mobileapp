package com.TeamPhich.deadline.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.inprogess_fragment.*

class InprogessFragment : AppCompatActivity() {
    //    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
//        inflater.inflate(R.layout.inprogess_fragment, container, false)
//
//    companion object {
//        fun newInstance(): InprogessFragment = InprogessFragment()
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inprogess_fragment)
        var arraytask : ArrayList<doneTasks> = ArrayList()
        arraytask.add(doneTasks("con pho","2/2/2222"))
        listview_inprogesstasks.adapter = CustomAdapter(this,arraytask)

    }
}