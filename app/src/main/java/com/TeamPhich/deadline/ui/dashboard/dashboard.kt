package com.TeamPhich.deadline.ui.dashboard

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.TeamPhich.deadline.R
import com.google.android.material.navigation.NavigationView

import android.os.Handler
import android.util.Log
import android.widget.*
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.responses.Space.Row
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.TeamPhich.deadline.ui.dashboard.details.Information
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activiy_showmenu.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.yourspace.*


class dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var target:Row=Row(0,"0")
    var listC: List<String> = listOf("x", "y", "z")
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    val color="#1c94e0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_showmenu)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)





        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(

            this, drawerLayout,toolbar,   0, 0
        )
        toggle.drawerArrowDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        getListSpace()
        val bottomNavigation : BottomNavigationView =findViewById(R.id.bottomNavigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //bat su kien cho avarta, an vao anh de hien thi information
        val intent = Intent(this, Information::class.java)
        avartauseringroup.setOnClickListener { view ->
            startActivity(intent)
        }
        val tablayoutTask = Tablayout_task.newInstance()
        openFragment(tablayoutTask)




    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

                // khi click vao nut create tasks se chuyen sang activity createtask
                R.id.create_task-> {
                    //                val createtask  = createtask.newInstance()
//                openFragment(createtask)
                    val tablayoutTask = Tablayout_task.newInstance()
                    openFragment(tablayoutTask)
                return@OnNavigationItemSelectedListener true
//                intent = Intent(this, createtask::class.java)
//
//                startActivity(intent)


            }
            // khi click vao nut peole se chuyen sang activity PeoleFragment
            R.id.people-> {
                val peopleFragment = PeopleFragment.newInstance()
                openFragment(peopleFragment)

                return@OnNavigationItemSelectedListener true
//                intent = Intent(this,PeopleFragment::class.java)
////                startActivity(intent)
            }
            //khi click vao message se show sang activity message
            R.id.message-> {
                val groupFragment = GroupFragment.newInstance()
                openFragment(groupFragment)

                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
    private  fun openFragment(fragment: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmnet_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_space -> {
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
        Log.d("tokenacc", sharedPreference.getToken().toString())
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

        setListViewHeightBasedOnChildren(_sListSpace)

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
                    view.setBackgroundColor(Color.parseColor(color))
                    if (response.success == true) {
                        sharedPreference.setTokenSpace(response.data.tokenSpace)
                        Log.d("Spacetoken",sharedPreference.getTokenSpace().toString())
                        Log.d("Tolen",sharedPreference.getToken().toString())

                    } else {
                        Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }

            }
            drawerLayout.closeDrawer(GravityCompat.START)

        }
        defaultSeclectListSpace(_sListSpace,listspace)


    }

    fun getspaceid(listspace: List<Row>, key: String): Int {
        listspace.forEach {
            if (key == it.name) return it.id
        }
        return 0
    }

    public fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter
            ?: // pre-condition
            return

        var totalHeight = 0
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }

        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
        listView.requestLayout()
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
    fun defaultSeclectListSpace(listView: ListView,listspace: List<Row>){
        val sharedPreference:SharedPreference= SharedPreference(this)
        Toast.makeText(
            applicationContext,
            _sListSpace.getItemAtPosition(0).toString(),
            Toast.LENGTH_LONG
        ).show()
        GlobalScope.launch(Dispatchers.Main) {
            try {

                val response = RetrofitClient.instance.getSpaceToken(
                    sharedPreference.getToken().toString(),
                    getspaceid(listspace, _sListSpace.getItemAtPosition(0).toString())).await()
                for (i in 0 until listView.childCount) {
                    listView.getChildAt(i).setBackgroundColor(Color.WHITE)
                }
                listView.getChildAt(0).setBackgroundColor(Color.parseColor(color))
                if (response.success == true) {
                    sharedPreference.setTokenSpace(response.data.tokenSpace)
                    Log.d("Spacetoken",sharedPreference.getTokenSpace().toString())
                    Log.d("Tolen",sharedPreference.getToken().toString())

                } else {
                    Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                        .show()
                }
            } catch (t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)

    }


}