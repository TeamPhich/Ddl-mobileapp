package com.TeamPhich.deadline.ui.dashboard

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.ui.dashboard.space_actyvity.createSpace_activity
import com.google.android.material.navigation.NavigationView

import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import android.widget.ListView
import android.widget.Toast
import com.TeamPhich.deadline.responses.Space.Row
import com.TeamPhich.deadline.responses.Space.getListSpaceRespone
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.yourspace.*


class dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var listC:List<String> = listOf("x", "y", "z")
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
                startActivity(Intent(this@dashboard, createSpace_activity::class.java))

            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun loaddialog(frag1: dialog_space){
        val fm= supportFragmentManager.beginTransaction()
        fm.replace(R.id.create_a_space,frag1)
        fm.commit()
//        val mDialogView  = LayoutInflater.from(this).inflate(R.layout.add_a_space,null);
//        val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
//        val mAlertDialog =mBuilder.show()
//        mDialogView.create.setOnClickListener {
//            mAlertDialog.dismiss()
//            val name =mDialogView.name_space.text.toString()
//
//        }
//        mDialogView.cancel.setOnClickListener {
//            mAlertDialog.dismiss()
//        }

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

    fun getListSpace(){
        val sharedPreference: SharedPreference = SharedPreference(this)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.instance.getListSpace(sharedPreference.getToken().toString()  ).await()
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
    fun showListSpace(listspace:List<Row>){
        val listnamespace: ArrayList<String> = ArrayList()
        listspace.forEach{
            listnamespace.add(it.name)
        }
        _sListSpace.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,listnamespace)
        _sListSpace.setOnItemClickListener{parent, view, position, id ->
            Toast.makeText(applicationContext,_sListSpace.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show()

        }
    }



}