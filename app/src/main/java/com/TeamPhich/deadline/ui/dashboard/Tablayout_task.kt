package com.TeamPhich.deadline.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.ui.dashboard.task.newTask
import kotlinx.android.synthetic.main.tablayout.view.*


class Tablayout_task : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tablayout, container, false)
        val viewPagerTask = view._bCreateTask
        val adapter = ViewPagerAdapter_task(childFragmentManager)

        adapter.addFragment(TaskFragment.newInstance("todo"), "TO DO")
        adapter.addFragment(TaskFragment.newInstance("in process"), "IN PROCESS")
        adapter.addFragment(TaskFragment.newInstance("in review"), "IN REVIEW")
        adapter.addFragment(TaskFragment.newInstance("done"), "DONE")
        viewPagerTask.adapter = adapter
        view.tabs.setupWithViewPager(viewPagerTask)
        addtask(view)
        return view
    }

    companion object {
        fun newInstance(): Tablayout_task = Tablayout_task()
    }

    fun addtask(view: View) {
        view._baddtask.setOnClickListener { view ->
            startActivity(Intent(requireActivity(), newTask::class.java))
        }
    }


}