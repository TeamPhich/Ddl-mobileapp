package com.TeamPhich.deadline.ui.dashboard

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.Row
import kotlinx.android.synthetic.main.people_fragment.*
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PeopleFragment() : Fragment() {
//        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
//        inflater.inflate(R.layout.people_fragment, container, false)


    override fun onCreateView(inflater: LayoutInflater,  container: ViewGroup?,  savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.people_fragment, container, false)
        var arraypeople : ArrayList<Group> = ArrayList()
        arraypeople.add(Group("diepninh", R.drawable.ic_people))
        val _listview=view.findViewById<ListView>(R.id._listview_people)
        val context: Context = context!!
        _listview.adapter = CustomAdapter_listviewgroup( context,arraypeople)

        getListPeople()
        return view
    }



    companion object {
        fun newInstance(): PeopleFragment = PeopleFragment()


    }
    fun getListPeople(){
        val context: Context = context!!
        val sharedPreference: SharedPreference = SharedPreference(context)
        Log.d("loi",sharedPreference.getTokenSpace().toString())

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.instance.getSpacemember(sharedPreference.getTokenSpace().toString()).await()
                if (response.success == true) {
                    Toast.makeText((context as dashboard).applicationContext, response.data.toString(), Toast.LENGTH_LONG)
                        .show()
                    Log.d("loi","lo3i")


                } else {
                    Toast.makeText((context as dashboard).applicationContext, response.reason, Toast.LENGTH_LONG)
                        .show()
                    Log.d("loi","loi2")

                }
            } catch (t: Throwable) {
                Log.d("loi","loi")
                Toast.makeText((context as dashboard).applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }


        }
    }
//    fun showListSpace(listspace: List<Row>) {
//
//
//
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.people_fragment)
//        var arraypeople : ArrayList<Group> = ArrayList()
//        arraypeople.add(Group("diepninh", R.drawable.ic_people))
//        _listview_people.adapter = CustomAdapter_listviewgroup(this ,arraypeople)
//        val than= PeopleFragment().
//
//    }
}