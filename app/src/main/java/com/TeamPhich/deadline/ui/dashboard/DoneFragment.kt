package com.TeamPhich.deadline.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.TeamPhich.deadline.R
import kotlinx.android.synthetic.main.done_fragment.*

class DoneFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.done_fragment)
        var arraytask : ArrayList<doneTasks> = ArrayList()
        arraytask.add(doneTasks("abc","xyz"))
        listview_donetasks.adapter = CustomAdapter(this@DoneFragment,arraytask)

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