package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.TeamPhich.deadline.R

//hàm này dùng để custom listview cho các hàm trạng thái todo inprogess inreview done , thuộc tính dduwowcj khai báo ở lớp doneTasks

class CustomAdapter_listviewtasks constructor(var context : Context, var mangcongviec :ArrayList<doneTasks>, var status: String ) : BaseAdapter() {
    class ViewHolder(row : View, context: Context, status: String) {
        var textviewtask: TextView
        var datetask: TextView
        var imageViewTask : ImageView

        init {
            textviewtask = row.findViewById(R.id._nameOftask_done) as TextView
            datetask = row.findViewById(R.id._timedone) as TextView
            imageViewTask = row.findViewById(R.id.popupMenuTask) as ImageView
            imageViewTask.setOnClickListener {
                val popupMenu = PopupMenu(context, it)
                popupMenu.inflate(R.menu.menu_popup_task)
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
                            Toast.makeText(context, "status", Toast.LENGTH_SHORT).show()
                            true
                        }
                        R.id._moveInprocess -> {
                            Toast.makeText(context, "in process", Toast.LENGTH_SHORT).show()
                            true
                        }
                        R.id._moveInreview -> {
                            Toast.makeText(context, "in review", Toast.LENGTH_SHORT).show()
                            true
                        }
                        R.id._movedone -> {
                            Toast.makeText(context, "done", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
            }
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View?
        var viewholder : ViewHolder
        if (convertView == null){
            var layoutInflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_task,null)
            viewholder =ViewHolder(view,context,status)
            view.tag = viewholder
        }else{
            view = convertView
            viewholder = convertView.tag as ViewHolder
        }
        var nhiemvu : doneTasks = getItem(position) as doneTasks
        viewholder.textviewtask.text= nhiemvu.task
        viewholder.datetask.text=nhiemvu.date
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
}
