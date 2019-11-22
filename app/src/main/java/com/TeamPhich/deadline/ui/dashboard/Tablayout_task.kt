package com.TeamPhich.deadline.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R

import kotlinx.android.synthetic.main.tablayout.view.*

class Tablayout_task :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tablayout, container, false)
        val viewPagerTask = view.viewPager
        val adapter = ViewPagerAdapter_task(childFragmentManager)
        adapter.addFragment(TaskFragment("todo"), "TO DO")
        adapter.addFragment(TaskFragment("in process"), "IN PROCESS")
        adapter.addFragment(TaskFragment("in review"), "IN REVIEW")
        adapter.addFragment(TaskFragment("done"), "DONE")
        viewPagerTask.adapter = adapter
        view.tabs.setupWithViewPager(viewPagerTask)
        return view
    }

    companion object {
        fun newInstance(): Tablayout_task= Tablayout_task()
    }

}