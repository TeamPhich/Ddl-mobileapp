package com.TeamPhich.deadline.ui.dashboard

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import com.TeamPhich.deadline.responses.findmember.Account
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.activity_login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList
import android.content.DialogInterface



class dialogTool {
    fun callDialoginsertMember(context: Context,fragment: PeopleFragment) {
        val dialogBuilder = AlertDialog.Builder(context).create()
        val inflater = fragment.layoutInflater
        val dialogView = inflater.inflate(com.TeamPhich.deadline.R.layout.dialog_insertmemspace, null)

        val edittextauto = dialogView.findViewById(com.TeamPhich.deadline.R.id.edt_comment) as AutoCompleteTextView
        val Submit = dialogView.findViewById(com.TeamPhich.deadline.R.id.buttonSubmit) as Button
        val Cancel = dialogView.findViewById(com.TeamPhich.deadline.R.id.buttonCancel) as Button
        val sharedPreference: SharedPreference = SharedPreference(context)


        autoTextinsertcheck(edittextauto,context)
        Submit.setOnClickListener {
            val username = edittextauto.text.toString().trim()
            if (username.isEmpty()) {
                edittextauto.error = "Space's name required"
                edittextauto.requestFocus()
                return@setOnClickListener
            }
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.importMemberToSpace(
                        sharedPreference.getTokenSpace().toString(),
                        username
                    ).await()
                    if (response.success == true) {
                        Toast.makeText(context, "OK", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, response.reason, Toast.LENGTH_LONG).show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
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
    fun checktextboxdialogchange(autoCompleteTextView: AutoCompleteTextView,context: Context,list: ArrayList<String>){

         val textView = autoCompleteTextView
         // Get the string array

         var arraysg: ArrayList<String> = ArrayList()
         list.forEach{
            arraysg.add(it)
             Log.d("username",it)
         }
        Log.d("username",arraysg.toString())
         // Create the adapter and set it to the AutoCompleteTextView
         val adapter = ArrayAdapter(context, R.layout.simple_list_item_1, arraysg)
         textView.setAdapter(adapter)


    }
    fun autoTextinsertcheck(autoCompleteTextView: AutoCompleteTextView,context: Context){
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                    if (s.toString().length==2){
                        val list=getsuggestPeople(context,s.toString(),autoCompleteTextView)
                    }



            }
        })
    }
    fun getsuggestPeople(context: Context,key:String,autoCompleteTextView: AutoCompleteTextView){
        val sharedPreference: SharedPreference = SharedPreference(context)
        val list=ArrayList<String>()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.instance.findmember(
                    sharedPreference.getToken().toString(),
                    key
                ).await()
                if (response.success == true) {
                    Log.d("listsuggest",response.toString())
                    response.data.accounts.forEach {
                        Log.d("test2",it.toString())
                        list.add(it.userName)
                    }
                    checktextboxdialogchange(autoCompleteTextView,context,list)
                    Log.d("test3",list.toString())
                } else {
                    Toast.makeText(context, response.reason, Toast.LENGTH_LONG).show()
                }
            } catch (t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
            }


        }
        Log.d("test3",list.toString())

    }
    fun calldialogswitchTask(context: Context,taskid:String,status:String){
        val builder = AlertDialog.Builder(context)

        // Set the alert dialog title
        builder.setTitle("App background color")

        // Display a message on alert dialog
        builder.setMessage("Ban muon chuyen task sang trang thai"+status)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES") { dialog, which ->
            val sharedPreference: SharedPreference = SharedPreference(context)
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.switchTask(
                        sharedPreference.getTokenSpace().toString(),taskid,status

                    ).await()
                    Log.d ("ưerwer",taskid)
                    Log.d("retret345",status)
                    if (response.success == true) {
                        Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, response.reason, Toast.LENGTH_LONG).show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
                }


            }
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(context, "You are not agree.", Toast.LENGTH_SHORT).show()
        }

//        builder.setNeutralButton("Cancel") { _, _ ->
//            Toast.makeText(requireContext(), "You cancelled the dialog.", Toast.LENGTH_SHORT).show()
//        }


        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    fun calldialag_deletetask(context: Context,taskid: String){
        val builder = AlertDialog.Builder(context)

        // Set the alert dialog title
        builder.setTitle("App background color")

        // Display a message on alert dialog
        builder.setMessage("Ban muon xoa task task sang trang thai")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES") { dialog, which ->
            val sharedPreference: SharedPreference = SharedPreference(context)
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.deleteTask(
                        sharedPreference.getTokenSpace().toString(),taskid

                    ).await()

                    if (response.success == true) {
                        Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, response.reason, Toast.LENGTH_LONG).show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
                }


            }
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(context, "You are not agree.", Toast.LENGTH_SHORT).show()
        }

//        builder.setNeutralButton("Cancel") { _, _ ->
//            Toast.makeText(requireContext(), "You cancelled the dialog.", Toast.LENGTH_SHORT).show()
//        }


        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



}
