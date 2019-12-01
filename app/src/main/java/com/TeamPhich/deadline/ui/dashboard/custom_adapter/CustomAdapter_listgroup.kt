package com.TeamPhich.deadline.ui.dashboard.custom_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.group.RowsGroup

//hàm này dùng để custom listview cho các hàm group vaf people , thuộc tính dduwowcj khai báo ở lớp Group
class CustomAdapter_listgroup(var context: Context, var mangnhom:ArrayList<RowsGroup>) : BaseAdapter() {
    class ViewHolder(row : View) {
        var textviewpeople: TextView


        init {
            textviewpeople = row.findViewById(R.id._nameOfgroup) as TextView

        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View?
        var viewholder : ViewHolder
        if (convertView == null){
            var layoutInflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_group,null)
            viewholder =
                ViewHolder(
                    view
                )
            view.tag = viewholder
        }else{
            view = convertView
            viewholder = convertView.tag as ViewHolder
        }
        var group : RowsGroup = getItem(position) as RowsGroup
        viewholder.textviewpeople.text= group.name
//        Glide
//            .with(context)
//            .load(nhom.imagesUrl)
//            .centerCrop()
//            .placeholder(R.drawable.ic_insert_photo)
//            .into(viewholder.imageviewpeople);
//
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