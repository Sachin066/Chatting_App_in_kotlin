package com.example.friendsdost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.friendsdost.activity.NumberActivity
import com.example.friendsdost.adapter.ViewPagerAdapter
import com.example.friendsdost.databinding.ActivityMainBinding
import com.example.friendsdost.ui.CallFragment
import com.example.friendsdost.ui.ChatFragment
import com.example.friendsdost.ui.StatusFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private  var binding : ActivityMainBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val fragmentArrayList = ArrayList<Fragment>()

        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null){
            startActivity(Intent(this,NumberActivity::class.java))
            finish()
        }



       val adapter = ViewPagerAdapter(this,supportFragmentManager,fragmentArrayList)

        binding!!.viewPager.adapter = adapter

        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
    }
}