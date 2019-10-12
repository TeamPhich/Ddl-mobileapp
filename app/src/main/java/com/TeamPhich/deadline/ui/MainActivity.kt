package com.TeamPhich.deadline.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.services.apiGithubService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiGithubService = apiGithubService()

        GlobalScope.launch (Dispatchers.Main){
            val githubResponse = apiGithubService.getDemo().await()
            textviewMain.text = githubResponse.avatarUrl
        }
    }
}
