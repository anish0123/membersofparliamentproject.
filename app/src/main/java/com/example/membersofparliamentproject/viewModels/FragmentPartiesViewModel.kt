package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.repository.ParliamentMemberRepository
import kotlinx.coroutines.launch

/**
 * This is the ViewModel for fragmentParties
 */
class FragmentPartiesViewModel(application: Application) : AndroidViewModel(application) {

    //Introducing live data objects for observing
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeDao()
    )

    //Introducing livedata for storing party list.
    private var _listedParties = MutableLiveData<List<String>>()
    val listedParties: LiveData<List<String>> = _listedParties


    /**
     * Funtion to get all the members parties of the parliament
     */
    fun getMemberParty() {
        viewModelScope.launch {
            _listedParties.value = parliamentMemberRepository.getMemberParty()
        }
    }


}

/**
 * This Factory class helps to initialize FragmentPartiesViewModel with application as
 * parameter (without it, we cannot have application context to create your AppDatabase)
 *
 *
 * Source: https://stackoverflow.com/questions/54419236/why-a-viewmodel-factory-is-needed-in-android
 */
class FragmentPartiesViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentPartiesViewModel::class.java)) {
            FragmentPartiesViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}