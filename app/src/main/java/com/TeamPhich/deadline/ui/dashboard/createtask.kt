package com.TeamPhich.deadline.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class createtask :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_createtask, container, false)

        val bottomNavigation : BottomNavigationView =view.findViewById(R.id.bottomcreate)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        return view
    }
    companion object{
        fun newInstance() : createtask= createtask()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
            R.id._btodo-> {
                val todoFragment = TodoFragment.newInstance()
                openFragment(todoFragment)
                return@OnNavigationItemSelectedListener true

            }

                    R.id._mSetAdmin-> {
                Log.d("diep","itemoriges")
                return@OnNavigationItemSelectedListener true

            }
                    R.id._icUpdate -> {
                        val updatetaskFragment =UpdatetaskFragment.newInstance()
                        openFragment(updatetaskFragment)

                        return@OnNavigationItemSelectedListener true

                    }


                    R.id._mdeleteMember-> {
                Log.d("diep","itemrevuew")
                return@OnNavigationItemSelectedListener true

            }

            R.id._bdone-> {
                Log.d("diep","itemdone")
                return@OnNavigationItemSelectedListener true

            }

        }
        false
    }
    private  fun openFragment(fragment: Fragment){
        val transaction = (context as dashboard).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}
//class createtask : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_createtask)
//        val bottomNavigation : BottomNavigationView =findViewById(R.id.bottomcreate)
//
//        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//    }
//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
            // click de show ra activity TodoFragment
//            R.id._btodo-> {
//                intent = Intent(this,TodoFragment::class.java)
//                startActivity(intent)
//            }
            // click de show ra activity InprogessFragment
//            R.id._binprogess-> {
//                val inprogessFragment  = InprogessFragment.newInstance()
//                openFragment(inprogessFragment)
//
//                return@OnNavigationItemSelectedListener true
//                intent = Intent(this,InprogessFragment::class.java)
//                startActivity(intent)
//            }
            // click de show ra activity InreviewIFragment
//            R.id._binreview-> {
//                val inreviewFragment = InreviewFragment.newInstance()
//                openFragment(inreviewFragment)
//
//                return@OnNavigationItemSelectedListener true
//                intent = Intent(this,InreviewFragment::class.java)
//                startActivity(intent)
//            }
            // click de show ra activity DoneFragment
//            R.id._bdone-> {
//                val todoFragment = TodoFragment.newInstance()
//                openFragment(todoFragment)
//
//                return@OnNavigationItemSelectedListener true
//                intent = Intent(this, DoneFragment::class.java)
//                startActivity(intent)
//            }
//
//        }
//        false
//    }
//    private  fun openFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
//
//}
