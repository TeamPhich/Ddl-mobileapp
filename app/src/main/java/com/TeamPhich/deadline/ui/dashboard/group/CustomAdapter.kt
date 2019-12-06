package com.TeamPhich.deadline.ui.dashboard.group

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.group.RowsGroup
import com.TeamPhich.deadline.responses.Space.group.chat.Message
import com.TeamPhich.deadline.ui.dashboard.dashboard
import com.bumptech.glide.Glide
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_mess_recived.view.*
import kotlinx.android.synthetic.main.item_mess_sent.view.*
import java.text.SimpleDateFormat
import java.util.*

//class sentadapter( val data: String) : RecyclerView.Adapter<sentadapter.ViewHolder_sentmess>()
////{
////
////    //this method is returning the view for each item in the list
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sentadapter.ViewHolder_sentmess {
////        val v = ViewHolder_sentmess(LayoutInflater.from(parent.context).inflate(R.layout.item_mess_sent, parent, false))
////        return (v)
////    }
////
////    //this method is binding the data on the list
////    override fun onBindViewHolder(holder: sentadapter.ViewHolder_sentmess, position: Int) {
////        holder.bindItems("hjdfdjgkdfhgkj")
////    }
////
////    //this method is giving the size of the list
////    override fun getItemCount(): Int {
////        return 1
////    }
////
////    //the class is hodling the list view
////    class ViewHolder_sentmess(itemView: View) : RecyclerView.ViewHolder(itemView) {
////
////        fun bindItems(body:String) {
////            val textViewtime = itemView.findViewById(R.id.text_messagesend_time) as TextView
////            val textViewbody  = itemView.findViewById(R.id.text_messagesend_body) as TextView
////            textViewtime.text = "thsdjfhsdjkf"
////            textViewbody.text = "jtkjerhtkjerhtekrjt"
////        }
////    }
////}
////class recievedadapter( val data: String) : RecyclerView.Adapter<recievedadapter.ViewHolder_sentmess>()
////{
////
////    //this method is returning the view for each item in the list
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recievedadapter.ViewHolder_sentmess {
////        val v = ViewHolder_sentmess(LayoutInflater.from(parent.context).inflate(R.layout.item_mess_recived, parent, false))
////        return (v)
////    }
////
////    //this method is binding the data on the list
////    override fun onBindViewHolder(holder: recievedadapter.ViewHolder_sentmess, position: Int) {
////        holder.bindItems("hjdfdjgkdfhgkj")
////    }
////
////    //this method is giving the size of the list
////    override fun getItemCount(): Int {
////        return 1
////    }
////
////    //the class is hodling the list view
////    class ViewHolder_sentmess(itemView: View) : RecyclerView.ViewHolder(itemView) {
////
////        fun bindItems(body:String) {
////            val textViewtime = itemView.findViewById(R.id.text_message_time) as TextView
////            val textViewbody  = itemView.findViewById(R.id.text_message_body) as TextView
////            val avatar=itemView.findViewById(R.id.image_message_profile) as ImageView
////            textViewtime.text = "thsdjfhsdjkf"
////            textViewbody.text = "jtkjerhtkjerhtekrjt"
////        }
////    }
////}
class senditem(val message: Message) : Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_messagesend_body.text=message.message
        viewHolder.itemView.text_messagesend_time.text=getDateTime(message.time)
    }

    override fun getLayout(): Int {
        return R.layout.item_mess_sent
    }
    fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("hh:mm")
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
            val netDate = Date(s.toLong())
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}
class receivecitem(val message: Message,val context: Context) : Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_message_body.text=message.message
        viewHolder.itemView.text_message_time.text=getDateTime(message.time)
        viewHolder.itemView.text_message_name.text=message.userName
        Glide
            .with(context)
            .load(message.imagesUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_insert_photo)
            .into(viewHolder.itemView.image_message_profile);

    }

    override fun getLayout(): Int {
        return R.layout.item_mess_recived
    }
    public fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("hh:mm")
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
            val netDate = Date(s.toLong())
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

}

class people() : Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.item_people2
    }

}

