package com.TeamPhich.deadline.ui.dashboard.task

import android.content.Context
import android.widget.ListView
import android.widget.Toast
import com.TeamPhich.deadline.responses.Space.RowX
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.dashboard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class showlistview {
//    fun showlistSpacePeople(listpeople: List<RowX>,listView: ListView,context: Context) {
//
//        var arraypeople: ArrayList<People> = ArrayList()
//        val context: Context = context!!
//        listpeople.forEach {
//            arraypeople.add(People(it.fullName, R.drawable.ic_people))
//        }
//        _listview_people.adapter = CustomAdapter_listviewpeople(context, arraypeople)
//        dashboard().setListViewHeightBasedOnChildren(_listview_people)
//        sukienlistpp(_listview_people)
//
//
//    }

    fun getListTask(context: Context) {

        val sharedPreference: SharedPreference = SharedPreference(context)


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getSpacemember(sharedPreference.getTokenSpace().toString())
                        .await()
                if (response.success == true) {


                } else {
                    Toast.makeText(
                        context.applicationContext,
                        response.reason,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(
                    context.applicationContext,
                    t.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }


        }
    }
}