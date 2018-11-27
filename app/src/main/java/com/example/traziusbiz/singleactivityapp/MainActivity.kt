package com.example.traziusbiz.singleactivityapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.traziusbiz.singleactivityapp.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}
