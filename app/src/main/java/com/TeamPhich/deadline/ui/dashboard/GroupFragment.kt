package com.TeamPhich.deadline.ui.dashboard


import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.group.Data
import com.TeamPhich.deadline.responses.Space.group.RowsGroup
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.custom_adapter.CustomAdapter_listgroup
import com.TeamPhich.deadline.ui.dashboard.group.Addpeopletogroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.TeamPhich.deadline.ui.dashboard.group.activity_chat
import kotlinx.android.synthetic.main.dialog_newgroup.view.*
import kotlinx.android.synthetic.main.group.*
import kotlinx.android.synthetic.main.group.view.*

class GroupFragment :Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.group , container, false)


        getlistgroup(view)


        view._addmoregroup.setOnClickListener {
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_newgroup, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
            val  mAlertDialog = mBuilder.show()
            mDialogView.buttonSubmit.setOnClickListener {
                mAlertDialog.dismiss()


            }

            mDialogView.buttonCancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        return view
    }



    companion object {
        fun newInstance(): GroupFragment = GroupFragment()
    }

    fun getlistgroup(view: View) {

        val sharedPreference: SharedPreference = SharedPreference(requireContext())
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getlistgroup(sharedPreference.getTokenSpace().toString())
                        .await()
                if (response.success == true) {
                    Toast.makeText(requireContext(),response.toString(),Toast.LENGTH_SHORT)
                    setadapterviewgrou(response.data,view)
                } else {
                    Toast.makeText(requireContext(), response.reason, Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun setadapterviewgrou(data: Data, view: View) {
        var arraygroup: ArrayList<RowsGroup> = ArrayList()
        val _listview = view.findViewById<ListView>(R.id.listview_group)
        data.rows1.forEach {
            arraygroup.add(it)
        }
        data.rows2.forEach {
            arraygroup.add(it)
        }


        _listview.adapter =
            CustomAdapter_listgroup(
                requireContext(),
                arraygroup
            )

        _listview.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), activity_chat::class.java)
//            val b = Bundle()
//            b.putInt("key", position) //Your id
            intent.putExtra("group_name",arraygroup.elementAt(position).name) //Put your id to your next Intent
            intent.putExtra("group_id",arraygroup.elementAt(position).groupId)
            startActivity(intent)



        }
    }
}