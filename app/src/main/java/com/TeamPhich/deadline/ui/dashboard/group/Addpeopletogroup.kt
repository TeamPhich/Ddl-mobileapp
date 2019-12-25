package com.TeamPhich.deadline.ui.dashboard.group

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.datapepleinsp
import com.TeamPhich.deadline.responses.Space.group.chat.grchatmem.RowNotInGr
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.custom_adapter.itemPeopleinSpace
import com.TeamPhich.deadline.ui.dashboard.dashboard
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_addpeopletogroup.*
import kotlinx.android.synthetic.main.people_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Addpeopletogroup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addpeopletogroup)
        val group_id = intent.getIntExtra("group_id", 0)
        Log.d("sdfjsdjkf",group_id.toString())
        getListPeople(group_id.toString())
    }

    fun getListPeople(groupid:String) {
        val context: Context = this
        val sharedPreference: SharedPreference = SharedPreference(context)


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getListppnotinspace(sharedPreference.getTokenSpace().toString(),groupid)
                        .await()
                if (response.success == true) {
                    loadlistpepple(response.data.rows,groupid)

                } else {
                    Toast.makeText(
                        (context as dashboard).applicationContext,
                        response.reason,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            } catch (t: Throwable) {
            }


        }
    }
    fun loadlistpepple(list:List<RowNotInGr>,groupid: String){
        var adapter = GroupAdapter<ViewHolder>()

        list.forEach {
            adapter.add(people(it,this,list,groupid))
        }


        _reyclerview_list_peopleinspace2.adapter=adapter

    }




}
