package com.example.membersofparliamentproject

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.database.ParliamentMembersViewModel
import com.example.membersofparliamentproject.network.ParliamentMemberApi
import kotlinx.coroutines.launch

class FragmentStartViewModel : ViewModel() {
    private lateinit var parliamentMembersViewModel: ParliamentMembersViewModel
    private lateinit var firstMember: ParliamentMembers
    private var members: MutableLiveData<List<ParliamentMembers>> = MutableLiveData()

    //Using viewModelScope for fetching the data in the background
    fun getMembers() {
        viewModelScope.launch {
            try {
                members.value = ParliamentMemberApi.retrofitService.getParliamentMembersList()
                //Working to get the JSON data into database
                val listedMembers = members.value?.toMutableList()
                if(listedMembers?.get(0) != null) {
                    firstMember = listedMembers[0]
                    Log.d(TAG,"This is the first member $firstMember")
                }
                parliamentMembersViewModel.addMember(firstMember)
            } catch (e: java.lang.Exception) {
                Log.d(ContentValues.TAG, "no luck in getting members: $e")
            }
        }
    }
}