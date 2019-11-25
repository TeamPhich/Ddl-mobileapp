package com.TeamPhich.deadline.ui.dashboard.custom_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.RowX
import com.bumptech.glide.Glide

//hàm này dùng để custom listview cho các hàm   people , thuộc tính dduwowcj khai báo ở lớp People
class CustomAdapter_listviewpeople(var context: Context, var mangbanbe:ArrayList<RowX>) : BaseAdapter() {
    class ViewHolder(row : View) {
        var textviewpeople: TextView
        var imageviewpeople: ImageView

        init {
            textviewpeople = row.findViewById(R.id._nameOfFriend) as TextView
            imageviewpeople = row.findViewById(R.id._avatar_friend) as ImageView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View?
        var viewholder : ViewHolder
        if (convertView == null){
            var layoutInflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_people,null)
            viewholder =
                ViewHolder(
                    view
                )
            view.tag = viewholder
        }else{
            view = convertView
            viewholder = convertView.tag as ViewHolder
        }
        var nhom : RowX = getItem(position) as RowX
        viewholder.textviewpeople.text= nhom.fullName
        Glide
            .with(context)
            .load(nhom.imagesUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_insert_photo)
            .into(viewholder.imageviewpeople);

        return view as View

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(p0: Int): Any {
        return mangbanbe.get(p0)
    }

    override fun getCount(): Int {
        return mangbanbe.size
    }

}