package com.example.membersofparliamentproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.membersofparliamentproject.database.ParliamentMembers

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var importedMembers: List<ParliamentMembers>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

    }
}

