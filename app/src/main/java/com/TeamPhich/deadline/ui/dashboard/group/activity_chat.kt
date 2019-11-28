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
import com.TeamPhich.deadline.responses.Space.group.chat.Message
import com.TeamPhich.deadline.responses.Space.group.chat.chatrp
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.ui.dashboard.dashboard
import com.google.gson.Gson
import org.json.JSONObject
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat.*


class activity_chat : AppCompatActivity() {
    var opts = IO.Options()
    val SockURL = "http://18.162.125.153/chat"
    var adapter = GroupAdapter<ViewHolder>()


    //    val sharedPreference: SharedPreference = SharedPreference(this)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val group_name = intent.getStringExtra("group_name")
        val group_id = intent.getIntExtra("group_id", 0)
        reyclerview_message_list.adapter = adapter


        val sharedPreference: SharedPreference = SharedPreference(this)
        createConnection(group_id, sharedPreference.getTokenSpace().toString())

    }


    fun createConnection(groupid: Int, token_space: String) {
        opts.query = "spaceToken=" + token_space + "&" + "group_id=" + groupid
        val socket = IO.socket(SockURL, opts)
        socket.connect()
        val json = JSONObject()
        json.put("offset", 0)
        socket.emit("messages.get", json)
        socket.on("currentMessages.get", Emitter.Listener { args ->

            val obj = args[0] as JSONObject
            val gson = Gson()
            var rp = gson.fromJson(args[0].toString(), chatrp::class.java)
            addmess(rp)



        })
        socket.on("new_messages.get", Emitter.Listener { args ->
            socket.emit("messages.get", json)




        })

        runOnUiThread(
            object : Runnable {
                override fun run() {
                    button_chatbox_send.setOnClickListener { view ->

                        val json = JSONObject()
                        json.put("message", edittext_chatbox.text.toString())
                        socket.emit("new_messages.post", json)

                    }
                }
            }
        )



    }

    fun addmess(chatrp: chatrp) {
        if (chatrp.messages.size > 0) {

            chatrp.messages.forEach {
                if (it.isUserMessages == true) {
                    runOnUiThread(
                        object : Runnable {
                            override fun run() {
                                adapter.add(senditem(it))
                            }
                        }
                    )

                } else if (it.isUserMessages == false) {
                    runOnUiThread(
                        object : Runnable {
                            override fun run() {
                                adapter.add(receivecitem(it,this@activity_chat))
                            }
                        }
                    )
                }
            }
        }

    }

}



