package com.example.membersofparliamentproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Introduced a supportFragment Manager and nav Controller
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        //Introducing action bar
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)


    }

    /**
     * Function for using the back button in the application.
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}

