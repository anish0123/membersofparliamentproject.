package com.example.membersofparliamentproject

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.database.ParliamentMembersViewModel
import com.example.`membersofparliamentproject`.databinding.ActivityMainBinding
import com.example.membersofparliamentproject.network.ParliamentMemberApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

