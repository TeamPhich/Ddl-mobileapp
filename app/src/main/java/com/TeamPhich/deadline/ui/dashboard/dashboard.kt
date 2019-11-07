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
import android.widget.Toast


class dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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

}