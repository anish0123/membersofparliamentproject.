package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMemberRepository
import com.example.membersofparliamentproject.fragments.FragmentParties
import kotlinx.coroutines.launch

/**
 * This is the ViewModel for fragmentParties
 */
class FragmentPartiesViewModel(application: Application): AndroidViewModel(application) {

    //Introducing live data objects for observing
    private val parliamentMemberRepository = ParliamentMemberRepository(AppDataBase.getDatabase(application).parliamentMembersDao(),AppDataBase.getDatabase(application).parliamentMembersExtraDao())
    private var _listedParties = MutableLiveData<List<String>>()
    val listedParties: LiveData<List<String>> = _listedParties

    fun getMemberParty() {
        viewModelScope.launch {
            _listedParties.value = parliamentMemberRepository.getMemberParty()
        }
    }


}

//Introducing a viewModel Factory
class FragmentPartiesViewModelFactory(val app:Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(FragmentPartiesViewModel::class.java)) {
            FragmentPartiesViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}