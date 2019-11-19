package com.TeamPhich.deadline.ui.dashboard

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.responses.Space.Row
import kotlinx.android.synthetic.main.people_fragment.*
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.*
import com.TeamPhich.deadline.responses.Space.RowX
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.android.synthetic.main.people_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PeopleFragment() : Fragment() {
//        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
//        inflater.inflate(R.layout.people_fragment, container, false)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.people_fragment, container, false)
        var arraypeople: ArrayList<Group> = ArrayList()
        arraypeople.add(Group("diepcute", R.drawable.pikachu))
        arraypeople.add(Group("diepcute", R.drawable.pikachu))
        arraypeople.add(Group("diepcute", R.drawable.pikachu))
        arraypeople.add(Group("diepcute", R.drawable.pikachu))
        arraypeople.add(Group("diepcute", R.drawable.pikachu))
        arraypeople.add(Group("diepcute", R.drawable.pikachu))
        arraypeople.add(Group("diepcute", R.drawable.pikachu))
        arraypeople.add(Group("diepcute", R.drawable.pikachu))

        val _listview = view.findViewById<ListView>(R.id._listview_people)
        val context: Context = context!!
        _listview.adapter = CustomAdapter_listviewgroup(context, arraypeople)
//        getListPeople()
//        View.OnClickListener { view ->
//            when (view.id) {
//                R.id._binsertMember -> {
////                    callDialoginsertMember()
//                    Log.d("diep","add a member")
//                }
//            }
//        }


        view._binsertMember.setOnClickListener { view ->
            callDialoginsertMember()
        }

        view._bDeleteSapce.setOnClickListener { view ->
            callDialogDeleteSpace()
        }
        view._bOutSpace.setOnClickListener { view ->
            callDialogOutSpace()
        }










        return view
    }


    companion object {
        fun newInstance(): PeopleFragment = PeopleFragment()


    }

    fun showlistSpacePeople(listpeople: List<RowX>) {

        var arraypeople: ArrayList<Group> = ArrayList()
        val context: Context = context!!
        listpeople.forEach {
            arraypeople.add(Group(it.fullName, R.drawable.ic_people))
        }
        _listview_people.adapter = CustomAdapter_listviewgroup(context, arraypeople)
        dashboard().setListViewHeightBasedOnChildren(_listview_people)


    }

    fun getListPeople() {
        val context: Context = context!!
        val sharedPreference: SharedPreference = SharedPreference(context)


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getSpacemember(sharedPreference.getTokenSpace().toString())
                        .await()
                if (response.success == true) {
                    showlistSpacePeople(response.data.rows)
                } else {
                    Toast.makeText(
                        (context as dashboard).applicationContext,
                        response.reason,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(
                    (context as dashboard).applicationContext,
                    t.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }


        }
    }


    fun callDialoginsertMember() {
        val dialogBuilder = AlertDialog.Builder(requireContext()).create()
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_insertmemspace, null)

        val editText = dialogView.findViewById(R.id.edt_comment) as EditText
        val Submit = dialogView.findViewById(R.id.buttonSubmit) as Button
        val Cancel = dialogView.findViewById(R.id.buttonCancel) as Button
        val sharedPreference: SharedPreference = SharedPreference(requireContext())

        Submit.setOnClickListener {
            val username = editText.text.toString().trim()
            if (username.isEmpty()) {
                editText.error = "Space's name required"
                editText.requestFocus()
                return@setOnClickListener
            }
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.importMemberToSpace(
                        sharedPreference.getTokenSpace().toString(),
                        username
                    ).await()
                    if (response.success == true) {
                        Toast.makeText(requireContext(), "OK", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), response.reason, Toast.LENGTH_LONG).show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
                }


            }

            dialogBuilder.dismiss()
        }
        Cancel.setOnClickListener {
            dialogBuilder.dismiss()
        }
        dialogBuilder.setView(dialogView)
        dialogBuilder.show()
    }

    fun callDialogOutSpace() {
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
                        Toast.makeText(requireContext(), "OK", Toast.LENGTH_LONG).show()
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
                        Toast.makeText(requireContext(), "OK", Toast.LENGTH_LONG).show()
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