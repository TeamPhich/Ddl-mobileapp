package com.TeamPhich.deadline.ui.dashboard.group

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.TeamPhich.deadline.R

import androidx.recyclerview.widget.RecyclerView
import com.TeamPhich.deadline.responses.Space.group.RowsGroup
import com.TeamPhich.deadline.ui.dashboard.custom_adapter.CustomAdapter_listgroup
import com.TeamPhich.deadline.ui.dashboard.dataclass
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_list_groupmember.*

import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.socket.client.IO

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.ui.dashboard.dashboard
import org.json.JSONObject
import io.socket.emitter.Emitter





class activity_chat : AppCompatActivity() {
    var opts = IO.Options()
    val SockURL="http://18.162.125.153/chat"



//    val sharedPreference: SharedPreference = SharedPreference(this)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val group_name=intent.getStringExtra("group_name")
        val group_id=intent.getIntExtra("group_id",0)
//        _blistPeople.setOnClickListener { view ->
//            val listGroupmember = Intent(this, activity_chat::class.java)
//            listGroupmember.putExtra("group_name",group_name) //Put your id to your next Intent
//            listGroupmember.putExtra("group_id",group_id)
//            startActivity(listGroupmember)
//
//        }
        val recyclerView = findViewById(R.id.reyclerview_message_list) as RecyclerView

        //adding a layoutmanager
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        //crating an arraylist to store users using the data class user

        //adding some dummy data to the list
        //creating our adapter
        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(stenclass())

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
        val sharedPreference:SharedPreference= SharedPreference(this)
        createConnection(group_id,sharedPreference.getTokenSpace().toString())

}

    fun createConnection(groupid:Int,token_space:String){
       opts.query = "spaceToken=" + token_space+"&"+"group_id="+groupid
        val socket= IO.socket(SockURL,opts)
        socket.connect()
        val json=JSONObject()
        json.put("offset",0)
        socket.emit("messages.get", json)
        socket.on("currentMessages.get", Emitter.Listener { args ->
           Log.d("sixautrai",args[0].toString())
        })






    }

}



