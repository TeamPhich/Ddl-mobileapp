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
        getListPeople()
    }

    fun getListPeople() {
        val context: Context = this
        val sharedPreference: SharedPreference = SharedPreference(context)


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getSpacemember(sharedPreference.getTokenSpace().toString())
                        .await()
                if (response.success == true) {
                    loadlistpepple(response.data.rows)

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
    fun loadlistpepple(list:List<datapepleinsp>){
        var adapter = GroupAdapter<ViewHolder>()

        list.forEach {
            adapter.add(people(it,this))
        }
        adapter.setOnItemClickListener { item, view ->
            dialoginsert()

        }



        _reyclerview_list_peopleinspace2.adapter=adapter
    }

    fun dialoginsert(){


        val builder = AlertDialog.Builder(this)
        builder.setMessage("Ban se them nguoi nay vao space ?")


        builder.setPositiveButton("YES"){dialog, which ->
            Toast.makeText(applicationContext,"Ok, we change the app background.",Toast.LENGTH_SHORT).show()

        }


        builder.setNegativeButton("No"){dialog,which ->
            Toast.makeText(applicationContext,"You are not agree.",Toast.LENGTH_SHORT).show()
        }


        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}
