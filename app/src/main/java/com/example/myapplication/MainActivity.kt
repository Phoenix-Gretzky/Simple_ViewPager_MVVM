package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
//    private var binding: MainActivityBind? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = MainActivityBinding.inflate(layoutInflater)
//        val view: View = binding.getRoot()
        setContentView(R.layout.activity_main)
    }
}