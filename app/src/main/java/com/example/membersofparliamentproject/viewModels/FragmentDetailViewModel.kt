package com.example.membersofparliamentproject.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMemberRepository
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.database.ParliamentMembersExtra
import kotlinx.coroutines.launch

/**
 * This is the viewModel for fragmentDetail.
 */
class FragmentDetailViewModel(application: Application) : AndroidViewModel(application) {

    //Initialising repository.
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao()
    )

    //Introducing live data object
    private var _extraInfo= MutableLiveData<List<ParliamentMembersExtra>>()
    val extraInfo: LiveData<List<ParliamentMembersExtra>> = _extraInfo

    //Function to save extraInfo to live data
    fun getAllExtraInfo() {
        viewModelScope.launch {
            _extraInfo.value = parliamentMemberRepository.getAllExtraInfo()
        }
    }


}

//Creating a view model factory for fragmentDetail viewModel
class FragmentDetailViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentDetailViewModel::class.java)) {
            FragmentDetailViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}