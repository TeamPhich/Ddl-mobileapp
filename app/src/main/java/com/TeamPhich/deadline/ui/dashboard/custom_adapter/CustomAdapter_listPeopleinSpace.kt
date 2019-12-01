package com.TeamPhich.deadline.ui.dashboard.custom_adapter

import android.content.Context
import android.view.View
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.datapepleinsp
import com.bumptech.glide.Glide
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_peopleinspace.view.*


class itemPeopleinSpace(val datapepleinsp: datapepleinsp,val context: Context) : Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView._name_thispp.text=datapepleinsp.fullName
        Glide
            .with(context)
            .load(datapepleinsp.imagesUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_insert_photo)
            .into(viewHolder.itemView._avatar_thispp)

    }

    override fun getLayout(): Int {
        return R.layout.item_peopleinspace
    }





}