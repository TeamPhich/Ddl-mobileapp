package com.TeamPhich.deadline.ui.dashboard.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.TeamPhich.deadline.R
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast
import com.TeamPhich.deadline.responses.Space.DataX
import com.TeamPhich.deadline.responses.Space.RowX
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient


import com.TeamPhich.deadline.ui.dashboard.custom_adapter.CustomAdapter_listviewpeople

import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener

import android.view.View
import android.widget.TimePicker


class newTask : AppCompatActivity() {
    var target: RowX = RowX("", "", 0, "", 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        choosedate()
        getListPeople()
        // an camcel de thoat
        _cancelcreate.setOnClickListener {
            finish()
        }
        _bCreateTask.setOnClickListener {
            Log.d("fjhidjhfjsdkf", target.id.toString())
            val sharedPreference: SharedPreference = SharedPreference(this)
            val description = _iTaskdescription.text.toString()
            val title = _iTasktittle.text.toString()

            if (description.isEmpty()) {
                _iTaskdescription.error = "Password required"
                _iTaskdescription.requestFocus()
                return@setOnClickListener
            }

            if (title.isEmpty()) {
                _iTasktittle.error = "User Name required"
                _iTasktittle.requestFocus()
                return@setOnClickListener
            }

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.createTask(
                        sharedPreference.getTokenSpace().toString(),
                        target.id,
                        title,
                        getunixdate(),
                        description
                    ).await()

                    if (response.success == true) {
                        Toast.makeText(applicationContext, "create ok", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(applicationContext, response.reason, Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_SHORT).show()
                }


            }
        }

    }


    fun getunixdate(): String {
        val textView: TextView = findViewById(R.id._selectday)
        val dateString = textView.text.toString()+" "+_selectedhour.text.toString()
        val date = SimpleDateFormat("dd.MM.yyyy HH:mm").parse(dateString)
        val unix = date.time / 1000L
        Log.d("oweirwpoeri",unix.toString())
        return unix.toString()
    }

    fun choosedate() {
        val textView: TextView = findViewById(R.id._selectday)
        textView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat)
                textView.text = sdf.format(cal.time)

            }

        textView.setOnClickListener {
            DatePickerDialog(
                this@newTask, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        _selectedhour.setOnClickListener {

            val hour = cal.get(Calendar.HOUR)
            val minute = cal.get(Calendar.MINUTE)

            val tpd =
                TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
                    _selectedhour.text = h.toString()+ ":" + m


                }), hour, minute, true)

            tpd.show()


        }


    }

    fun showlistSpacePeople(respone: DataX) {

        var arraypeople: ArrayList<RowX> = ArrayList()

        respone.rows.forEach {
            arraypeople.add(it)
        }
        _selectedmember.adapter =


            CustomAdapter_listviewpeople(
                this,
                arraypeople
            )
////        dashboard().setListViewHeightBasedOnChildren(_listview_people)
//        sukienlistpp(_listview_people)
        _selectedmember.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                target = arraypeople.elementAt(position)

            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

    }

    fun getListPeople() {

        val sharedPreference: SharedPreference = SharedPreference(this)


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getSpacemember(sharedPreference.getTokenSpace().toString())
                        .await()
                if (response.success == true) {
                    showlistSpacePeople(response.data)

                } else {
                    Toast.makeText(
                        applicationContext,
                        response.reason,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            } catch (t: Throwable) {
            }


        }
    }


}