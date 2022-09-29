package com.example.membersofparliamentproject.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParliamentMembersViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<ParliamentMembers>>
    private val repository: ParliamentMemberRepository

    init {
       val parliamentMembersDao = AppDataBase.getDatabase(application).parliamentMembersDao()
           repository = ParliamentMemberRepository(parliamentMembersDao)
           readAllData = repository.readAllData

    }
    fun addMember(member: ParliamentMembers) {
        viewModelScope.launch(Dispatchers.IO) {
           repository.addMember(member)
        }
    }
}