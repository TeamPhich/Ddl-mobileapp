package com.TeamPhich.deadline.ui.dashboard.group

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.datapepleinsp
import com.TeamPhich.deadline.responses.Space.group.RowsGroup
import com.TeamPhich.deadline.responses.Space.group.chat.Message
import com.TeamPhich.deadline.responses.Space.group.chat.grchatmem.RowNotInGr
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.dashboard
import com.bumptech.glide.Glide
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.item_mess_recived.view.*
import kotlinx.android.synthetic.main.item_mess_sent.view.*
import kotlinx.android.synthetic.main.item_people2.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
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
class senditem(val message: Message) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_messagesend_body.text = message.message
        viewHolder.itemView.text_messagesend_time.text = getDateTime(message.time)
    }

    override fun getLayout(): Int {
        return R.layout.item_mess_sent
    }

    fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("hh:mm")
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
            val netDate = Date(s.toLong() + 3600 * 7*1000)
            return sdf.format(netDate)
        } catch (e: Exception) {

        }
        return "--"
    }
}

class receivecitem(val message: Message, val context: Context) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_message_body.text = message.message
        viewHolder.itemView.text_message_time.text = getDateTime(message.time)
        viewHolder.itemView.text_message_name.text = message.userName
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

    fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("hh:mm")
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
            val netDate = Date(s+(3600*7*1000))
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

}

class people(
    val datapepleinsp: RowNotInGr,
    val context: Context,
    val L2ist: List<RowNotInGr>,
    val grid: String
) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        Glide
            .with(context)
            .load(datapepleinsp.imagesUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_insert_photo)
            .into(viewHolder.itemView._avatar_peo);
        viewHolder.itemView._name_peo2.text = datapepleinsp.fullName
        viewHolder.itemView.textView456.setOnClickListener {
            Toast.makeText(context, "them thanh cong", Toast.LENGTH_SHORT)

            val builder = AlertDialog.Builder(context)
            val sharedPreference: SharedPreference = SharedPreference(context)
            fun vipo(){
                it.isVisible=false
            }

            builder.setMessage("Xac nhan them")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->


                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        val response =
                            RetrofitClient.instance.importpptogroup(
                                sharedPreference.getTokenSpace().toString(),
                                L2ist[position].spaces_member_id.toString(),
                                grid
                            )
                                .await()
                        val cont=context!!
                        if (response.success == true) {
                            Log.d("sdfsdfsdfer34","sdfsdfwerwerew")
                            Toast.makeText(cont as dashboard, "them thanh cong", Toast.LENGTH_SHORT)
                            vipo()

                        } else {

                            Toast.makeText(cont as dashboard, "them thanh cong", Toast.LENGTH_SHORT)

                        }
                    } catch (t: Throwable) {
                    }
                }
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(
                    context,
                    android.R.string.no, Toast.LENGTH_SHORT
                ).show()
            }
            builder.show()
        }

    }

    override fun getLayout(): Int {
        return R.layout.item_people2
    }





}

