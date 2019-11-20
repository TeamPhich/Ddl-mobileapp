package com.TeamPhich.deadline.ui.dashboard

import android.R
import android.app.AlertDialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import com.TeamPhich.deadline.responses.findmember.Account
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

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
}