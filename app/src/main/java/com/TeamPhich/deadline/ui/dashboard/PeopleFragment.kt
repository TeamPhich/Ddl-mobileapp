package com.TeamPhich.deadline.ui.dashboard

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import android.widget.*
import com.TeamPhich.deadline.responses.Space.DataX
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient


import com.TeamPhich.deadline.ui.dashboard.custom_adapter.itemPeopleinSpace
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import kotlinx.android.synthetic.main.people_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PeopleFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.people_fragment, container, false)
        getListPeople(view)
        addmember(view)
        return view
    }

    companion object {
        fun newInstance():PeopleFragment  = PeopleFragment()
    }




    fun showlistSpacePeople(view: View,data: DataX,context: Context) {

            var adapter = GroupAdapter<ViewHolder>()
            data.rows.forEach {
                adapter.add(itemPeopleinSpace(it,context))
            }

            view._reyclerview_list_peopleinspace.adapter=adapter




    }
    fun getListPeople(view: View) {
        val context: Context = context!!
        val sharedPreference: SharedPreference = SharedPreference(context)


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getSpacemember(sharedPreference.getTokenSpace().toString())
                        .await()
                if (response.success == true) {
                    showlistSpacePeople(view,response.data,context)

                } else {
                    Toast.makeText(
                        (context as dashboard).applicationContext,
                        response.reason,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            } catch (t: Throwable) {
            }


        }
    }
    fun addmember(view: View)
    {
        view._bAddPeople.setOnClickListener { view ->
            dialogTool().callDialoginsertMember(requireContext(),this)
        }
    }







    fun callDialogDeleteSpace() {
        val builder = AlertDialog.Builder(requireContext())

        // Set the alert dialog title
        builder.setTitle("App background color")

        // Display a message on alert dialog
        builder.setMessage("Ban thuc su muon thoat khoi space?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES") { dialog, which ->
            val sharedPreference: SharedPreference = SharedPreference(requireContext())
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.memberOutSpace(
                        sharedPreference.getTokenSpace().toString()
                    ).await()
                    if (response.success == true) {

                    } else {
                        Toast.makeText(requireContext(), response.reason, Toast.LENGTH_LONG).show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(requireContext(), "You are not agree.", Toast.LENGTH_SHORT).show()
        }

//        builder.setNeutralButton("Cancel") { _, _ ->
//            Toast.makeText(requireContext(), "You cancelled the dialog.", Toast.LENGTH_SHORT).show()
//        }


        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}


//
//


