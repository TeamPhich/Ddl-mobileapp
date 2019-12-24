package com.TeamPhich.deadline.ui.dashboard.custom_adapter

import android.content.Context
import android.content.Intent
import android.media.FaceDetector
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.datapepleinsp
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.dashboard
import com.bumptech.glide.Glide
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_peopleinspace.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class itemPeopleinSpace(val datapepleinsp: datapepleinsp, val context: Context) :
    Item<ViewHolder>() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView._name_thispp.text = datapepleinsp.fullName
        Glide
            .with(context)
            .load(datapepleinsp.imagesUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_insert_photo)
            .into(viewHolder.itemView._avatar_thispp)
        if (datapepleinsp.role_name == "admin") {
            viewHolder.itemView._isadmin.isChecked = true
        } else {
            viewHolder.itemView._isUser.isChecked = true
        }

        viewHolder.itemView.radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = viewHolder.itemView.findViewById(checkedId)
                var nextRole = 0
                Log.d("djfhsdkjfhsdjkf", radio.text.toString())
                if (radio.text.toString() == "Admin") nextRole = 1
                else nextRole = 2
                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        val sharedPreference: SharedPreference = SharedPreference(context)
                        val response =
                            RetrofitClient.instance.updateRole(
                                sharedPreference.getTokenSpace().toString(),
                                datapepleinsp.id,
                                nextRole
                            )
                                .await()
                        if (response.success == true) {
                            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()
                        } else {
//                            viewHolder.itemView._isadmin.isChecked = !viewHolder.itemView._isadmin.isChecked
//                            viewHolder.itemView._isUser.isChecked = !viewHolder.itemView._isUser.isChecked
                            Toast.makeText(context, response.reason, Toast.LENGTH_SHORT).show()
                        }
                    } catch (t: Throwable) {
                        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
//                        viewHolder.itemView._isadmin.isChecked = !viewHolder.itemView._isadmin.isChecked
//                        viewHolder.itemView._isUser.isChecked = !viewHolder.itemView._isUser.isChecked
                    }


                }


            })
    }

    override fun getLayout(): Int {
        return R.layout.item_peopleinspace
    }


}