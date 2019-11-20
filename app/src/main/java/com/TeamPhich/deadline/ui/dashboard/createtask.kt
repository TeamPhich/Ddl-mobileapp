package com.TeamPhich.deadline.ui.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_createtask.view.*
import kotlinx.android.synthetic.main.update_task.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class createtask :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_createtask, container, false)

        val bottomNavigation : BottomNavigationView =view.findViewById(R.id.bottomcreate)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        view._123.setOnClickListener { view -> callDialogaddtask()   }
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

                    R.id._binprogess-> {
                        val inprogessFragment = InprogessFragment.newInstance()
                        openFragment(inprogessFragment)
                return@OnNavigationItemSelectedListener true

            }



                    R.id._bỉreview-> {
                        val inreviewFragment = InreviewFragment.newInstance()
                        openFragment(inreviewFragment)
                return@OnNavigationItemSelectedListener true

            }

            R.id._bdone-> {
                val doneFragment = DoneFragment.newInstance()
                openFragment(doneFragment)
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
    fun callDialogaddtask() {
        val dialogBuilder = AlertDialog.Builder(requireContext()).create()
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_task, null)

        val addtask = dialogView.findViewById(R.id._bupdate) as Button

        val Cancel = dialogView.findViewById(R.id._bcancelupdate) as Button
        val sharedPreference: SharedPreference = SharedPreference(requireContext())

        addtask.setOnClickListener {
            val task= _bupdate.text.toString().trim()
            val date = _bupdate.text.toString().trim()
            if (task.isEmpty()) {
                _bupdate.error = "Space's name required"
                _bupdate.requestFocus()
                return@setOnClickListener
            }
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = RetrofitClient.instance.importMemberToSpace(
                        sharedPreference.getTokenSpace().toString(),
                        task
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
