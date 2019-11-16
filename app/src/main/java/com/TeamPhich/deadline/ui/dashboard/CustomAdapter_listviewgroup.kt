package com.TeamPhich.deadline.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.TeamPhich.deadline.R

class CustomAdapter_listviewgroup(var context: Context, var mangnhom:ArrayList<Group> ) : BaseAdapter() {
    class ViewHolder(row : View) {
        var textviewgroup: TextView
        var imageviewgroup: ImageView

        init {
            textviewgroup = row.findViewById(R.id._nameOfgroup) as TextView
            imageviewgroup = row.findViewById(R.id._avatar_group) as ImageView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View?
        var viewholder : ViewHolder
        if (convertView == null){
            var layoutInflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_group,null)
            viewholder =ViewHolder(view)
            view.tag = viewholder
        }else{
            view = convertView
            viewholder = convertView.tag as ViewHolder
        }
        var nhom : Group = getItem(position) as Group
        viewholder.textviewgroup.text= nhom.namegroup
        viewholder.imageviewgroup.setImageResource(nhom.image)
        return view as View

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(p0: Int): Any {
        return mangnhom.get(p0)
    }

    override fun getCount(): Int {
        return mangnhom.size
    }
}