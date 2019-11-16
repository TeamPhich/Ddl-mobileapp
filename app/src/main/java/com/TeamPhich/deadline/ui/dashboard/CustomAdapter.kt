package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.TeamPhich.deadline.R

class CustomAdapter constructor(var context : Context, var mangcongviec :ArrayList<doneTasks> ) : BaseAdapter() {
    class ViewHolder(row : View) {
        var textviewtask: TextView
        var datetask: TextView

        init {
            textviewtask = row.findViewById(R.id._nameOftask_done) as TextView
            datetask = row.findViewById(R.id._timedone) as TextView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View?
        var viewholder : ViewHolder
        if (convertView == null){
            var layoutInflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_done,null)
            viewholder =ViewHolder(view)
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
