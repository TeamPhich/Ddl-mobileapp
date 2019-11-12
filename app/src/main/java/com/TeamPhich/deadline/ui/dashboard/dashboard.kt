package com.TeamPhich.deadline.ui.dashboard

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.TeamPhich.deadline.R
import com.google.android.material.navigation.NavigationView

import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.responses.Space.Row
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.yourspace.*

import android.widget.EditText


class dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var listC: List<String> = listOf("x", "y", "z")
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_showmenu)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(

            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        getListSpace()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_space -> {
//               loaddialog(frag1 = dialog_space())
//                val builder = AlertDialog.Builder(this)
//                builder.setTitle("Androidly Alert")
//                builder.setMessage("We have a messac xcge")
//                //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
//
//                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
//                    Toast.makeText(applicationContext,
//                        android.R.string.yes, Toast.LENGTH_SHORT).show()
//                }
//
//                builder.setNegativeButton(android.R.string.no) { dialog, which ->
//                    Toast.makeText(applicationContext,
//                        android.R.string.no, Toast.LENGTH_SHORT).show()
//                }
//
//                builder.setNeutralButton("Maybe") { dialog, which ->
//                    Toast.makeText(applicationContext,
//                        "Maybe", Toast.LENGTH_SHORT).show()
//                }
//                builder.show()
                callDialogSpace()

            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            System.exit(-1)

            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    fun getListSpace() {
        val sharedPreference: SharedPreference = SharedPreference(this)
        Log.d("AAAAE", sharedPreference.getToken().toString())
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClient.instance.getListSpace(sharedPreference.getToken().toString())
                        .await()
                if (response.success == true) {
                    showListSpace(response.data.rows)


                } else {
                    Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }


        }

    }

    fun showListSpace(listspace: List<Row>) {
        val listnamespace: ArrayList<String> = ArrayList()
        val sharedPreference: SharedPreference = SharedPreference(this)
        listspace.forEach {
            listnamespace.add(it.name)
        }

        _sListSpace.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listnamespace)
        _sListSpace.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                applicationContext,
                _sListSpace.getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG
            ).show()
            GlobalScope.launch(Dispatchers.Main) {
                try {

                    val response = RetrofitClient.instance.getSpaceToken(
                    sharedPreference.getToken().toString(),
                    getspaceid(listspace, _sListSpace.getItemAtPosition(position).toString())).await()
                    for (i in 0 until parent.childCount) {
                    parent.getChildAt(i).setBackgroundColor(Color.WHITE)
                    }
                    view.setBackgroundColor(Color.GREEN)
                    if (response.success == true) {

                        Toast.makeText(
                            applicationContext,
                            response.data.tokenSpace,
                            Toast.LENGTH_LONG
                        )
                            .show()
                        sharedPreference.setTokenSpace(response.data.tokenSpace)


                    } else {
                        Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }


            }

        }
    }

    fun getspaceid(listspace: List<Row>, key: String): Int {
        listspace.forEach {
            if (key == it.name) return it.id
        }
        return 0
    }


    fun callDialogSpace(){
        val dialogBuilder = AlertDialog.Builder(this).create()
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.activity_dialog_space, null)

        val editText = dialogView.findViewById(R.id.edt_comment) as EditText
        val Submit = dialogView.findViewById(R.id.buttonSubmit) as Button
        val Cancel = dialogView.findViewById(R.id.buttonCancel) as Button
        val sharedPreference: SharedPreference = SharedPreference(this)

        Submit.setOnClickListener {
            val spacename2=editText.text.toString().trim()
            if (spacename2.isEmpty()) {
                editText.error = "Space's name required"
                editText.requestFocus()
                return@setOnClickListener
            }
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.createSpace(sharedPreference.getToken().toString(), spacename2 ).await()
                    if (response.success == true) {
                        Toast.makeText(applicationContext, "OK", Toast.LENGTH_LONG)
                            .show()
                        getListSpace()

                    } else {
                        Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
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


}