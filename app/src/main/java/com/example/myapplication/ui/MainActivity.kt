package com.example.myapplication.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.adapter.ViewPagerAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.getRoot()
        setContentView(view.rootView)


        setViewProperties();
    }

    private fun setViewProperties()
    {


        binding?.backButton?.setOnClickListener{

            onBackPressedDispatcher.onBackPressed();
        }
        binding?.correct?.setImageResource(R.drawable.accept)


        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding!!.viewPager.adapter = adapter

        TabLayoutMediator(binding!!.tabLayout, binding!!.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Application"
                1 -> tab.text = "Settings"
            }
        }.attach()

        // Add a white line below the currently active tab
        binding!!.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                tab?.let {
//                    val tabView = it.customView as LinearLayout
//                    val whiteLine = View(this@MainActivity)
//                    whiteLine.setBackgroundColor(Color.WHITE)
//                    whiteLine.layoutParams = LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        resources.getDimensionPixelSize(R.dimen.tab_layout_selected_line_height)
//                    )
//                    tabView.addView(whiteLine)
//                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // No action needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // No action needed
            }
        })

    }
}