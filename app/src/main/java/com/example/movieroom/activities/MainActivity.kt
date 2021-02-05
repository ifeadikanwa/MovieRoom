package com.example.movieroom.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieroom.R
import com.example.movieroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setup databinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //nav controller
        val navController = this.findNavController(R.id.navHostFragment)

        //setup bottom navigation bar
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController )

        //setup navigationUI for the action bar so the up button is controlled by navigation backstack setting
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    //use the nav controller to navigate up
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }
}