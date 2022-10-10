package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMembersExtra
import com.example.membersofparliamentproject.repository.ParliamentMemberRepository
import kotlinx.coroutines.launch

/**
 * This is the viewModel for fragmentDetail.
 */
class FragmentDetailViewModel(application: Application) : AndroidViewModel(application) {

    //Initialising repository.
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeDao()
    )

    //Introducing live data object
    private var _extraInfo = MutableLiveData<List<ParliamentMembersExtra>>()
    val extraInfo: LiveData<List<ParliamentMembersExtra>> = _extraInfo
    var _extraInfoById = MutableLiveData<ParliamentMembersExtra>()

    /**
     * Function to save extraInfo to live data
     */

    fun getAllExtraInfo() {
        viewModelScope.launch {
            _extraInfo.value = parliamentMemberRepository.getAllExtraInfo()
        }
    }

    /**
     * Funtion to get extraInfo according to hetekaId
     */
    fun getExtraInfo(hetekaId: Int) {
        viewModelScope.launch {
            _extraInfoById.value = parliamentMemberRepository.getExtraInfo(hetekaId)
        }
    }


}

/**
 * This Factory class helps to initialize FragmentDetailViewModel with application as
 * parameter (without it, we cannot have application context to create your AppDatabase)
 *
 *
 * Source: https://stackoverflow.com/questions/54419236/why-a-viewmodel-factory-is-needed-in-android
 */
class FragmentDetailViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentDetailViewModel::class.java)) {
            FragmentDetailViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}