package com.TeamPhich.deadline.ui.dashboard.custom_adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.task.task
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.*
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


//hàm này dùng để custom listview cho các hàm trạng thái todo inprogess inreview done , thuộc tính dduwowcj khai báo ở lớp doneTasks

class CustomAdapter_listviewtasks (var context : Context, var mangcongviec :ArrayList<task>, var status: String,val taskFragment: TaskFragment) : BaseAdapter() {

    class ViewHolder(row : View, context: Context, status: String, task: task,taskFragment: TaskFragment) {
        var textviewtask: TextView
        var datetask: TextView
        var imageuser : ImageView
        var descriptiontask :TextView

        init {
            textviewtask = row.findViewById(R.id._nameOftask_done) as TextView
            datetask = row.findViewById(R.id._timedone) as TextView
            imageuser= row.findViewById(R.id._avatar_group) as ImageView
            descriptiontask= row.findViewById(R.id._description) as TextView

            row.setOnClickListener {
                dialogTool().callDiaglogTaskInfo(context, taskFragment,task)
            }


            row.setOnLongClickListener {
                row.visibility=View.VISIBLE

                val popupMenu = PopupMenu(context, it)
                popupMenu.inflate(R.menu.menu_popup_task)
                if(task.isMember&&task.isSuperAdmin==false){
                    popupMenu.menu.removeItem(R.id._deleteTask)
                }
                if(task.isSuperAdmin&&(task.isCreator==false&&!task.isMember)){
                    popupMenu.menu.removeItem(R.id._moveTodo)
                    popupMenu.menu.removeItem(R.id._moveInprocess)
                    popupMenu.menu.removeItem(R.id._moveInreview)
                    popupMenu.menu.removeItem(R.id._movedone)
                }
                if (!task.isMember&&!task.isCreator&&!task.isSuperAdmin){
                    popupMenu.menu.removeItem(R.id._moveTodo)
                    popupMenu.menu.removeItem(R.id._moveInprocess)
                    popupMenu.menu.removeItem(R.id._moveInreview)
                    popupMenu.menu.removeItem(R.id._movedone)
                    popupMenu.menu.removeItem(R.id._deleteTask)
                }

                when(status) {
                    "todo" -> {
                        popupMenu.menu.removeItem(R.id._moveTodo)
                    }
                    "in process" -> {
                        popupMenu.menu.removeItem(R.id._moveInprocess)
                    }
                    "in review" -> {
                        popupMenu.menu.removeItem(R.id._moveInreview)
                    }
                    "done" -> {
                        popupMenu.menu.removeItem(R.id._movedone)
                    }
                }

                popupMenu.setOnMenuItemClickListener { item ->
                    when( item.itemId) {
                        R.id._moveTodo -> {
                            dialogTool().calldialogswitchTask(context,task.id.toString(),"todo")
                            true
                        }
                        R.id._moveInprocess -> {
                            dialogTool().calldialogswitchTask(context,task.id.toString(),"in process")
                            true
                        }
                        R.id._moveInreview -> {
                            dialogTool().calldialogswitchTask(context,task.id.toString(),"in review")
                            true
                        }
                        R.id._movedone -> {

                            dialogTool().calldialogswitchTask(context,task.id.toString(),"done")
                            true
                        }
                        R.id._deleteTask-> {
                            dialogTool().calldialag_deletetask(context,task.id.toString())
                            true
                        }
                        else -> false
                    }
                }

                popupMenu.show()
                false
            }
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View?
        var viewholder : ViewHolder
        if (convertView == null){
            var layoutInflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_task,null)
            var task : task = getItem(position) as task

            viewholder =
                ViewHolder (
                    view,
                    context,
                    status,
                    task,
                    taskFragment
                )
            view.tag = viewholder
        }else{
            view = convertView
            viewholder = convertView.tag as ViewHolder
        }
        var nhiemvu : task = getItem(position) as task
        viewholder.textviewtask.text= nhiemvu.title
        viewholder.datetask.text=gettextdatefromunix(nhiemvu.deadline).toString()
//        viewholder.imageuser.setImageResource(nhiemvu.image)
        setimage(nhiemvu.memberId.toString(),viewholder)
        viewholder.descriptiontask.text=nhiemvu.description
        return view as View

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(p0: Int): Any {
        return mangcongviec.get(p0)
    }

    override fun getCount(): Int {
        return mangcongviec.size
    }
    fun setimage(userid:String,viewholder:ViewHolder){
        Log.d("rtytry","")
        val sharedPreference: SharedPreference = SharedPreference(context)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.instance.getSpacememberbyid(sharedPreference.getTokenSpace().toString(),userid).await()
                if (response.success == true) {
                    Log.d("sdfsdfsdf",response.data.rows.elementAt(0).imagesUrl)
                    Glide
                        .with(context)
                        .load(response.data.rows.elementAt(0).imagesUrl)
                        .centerCrop()
                        .placeholder(R.drawable.ic_insert_photo)
                        .into(viewholder.imageuser)

                } else {
                    Toast.makeText(context, response.reason, Toast.LENGTH_LONG)
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
            }


        }
    }
    fun gettextdatefromunix(unix: Long):String{
        val sdf = java.text.SimpleDateFormat("dd-MM-yyyy hh:mm")
        val date = java.util.Date(unix * 1000+1000*7*3600)

        return sdf.format(date)
    }
}
