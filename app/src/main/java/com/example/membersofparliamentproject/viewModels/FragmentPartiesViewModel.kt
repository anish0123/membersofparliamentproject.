package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMemberRepository
import com.example.membersofparliamentproject.fragments.FragmentParties

class FragmentPartiesViewModel(application: Application): AndroidViewModel(application) {

    private val parliamentMemberRepository = ParliamentMemberRepository(AppDataBase.getDatabase(application).parliamentMembersDao())
    val listedParties = parliamentMemberRepository.getMemberParty()


}

class FragmentPartiesViewModelFactory(val app:Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(FragmentPartiesViewModel::class.java)) {
            FragmentPartiesViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}