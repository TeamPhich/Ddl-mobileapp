package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.os.Bundle
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
import com.TeamPhich.deadline.ui.MessageEvent
import com.TeamPhich.deadline.ui.dashboard.custom_adapter.CustomAdapter_listviewtasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

// nhiemvu duoc giao hoan thanh
class TaskFragment(var status: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_fragment, container, false)
        var arraytask: ArrayList<task> = ArrayList()
        when (status) {
            "todo" -> {
                getlist(view, status, arraytask)
            }
            "in process" -> {
                getlist(view, status, arraytask)
            }
            "in review" -> {
                getlist(view, status, arraytask)
            }
            "done" -> {
                getlist(view, status, arraytask)
            }
        }
        return view
    }


    companion object {
        fun newInstance(status: String): TaskFragment = TaskFragment(status)
    }


    fun getlist(view: View?, status: String, arraytask: ArrayList<task>) {
        val sharedPreference: SharedPreference = SharedPreference(requireContext())
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.instance.showSpacetask(
                    sharedPreference.getTokenSpace().toString(),
                    status
                ).await()
                if (response.success) {
                    response.data.rows.forEach {
                        arraytask.add(it)
                    }
                    val _listview = view?.findViewById<ListView>(R.id.listview_donetasks)
                    val context: Context = context!!
                    _listview?.adapter =
                        CustomAdapter_listviewtasks(
                            context,
                            arraytask,
                            status
                        )
                } else {
                    Toast.makeText(requireContext(), response.reason, Toast.LENGTH_LONG)
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent) {
        var arraytask: ArrayList<task> = ArrayList()
        getlist(this.view, status, arraytask)

    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}