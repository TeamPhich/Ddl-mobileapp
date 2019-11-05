package com.TeamPhich.deadline.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.TeamPhich.deadline.R

 class dialog_space : Fragment(){
     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         return LayoutInflater.from(container?.context).inflate(R.layout.add_a_space,container,false)
     }
 }
