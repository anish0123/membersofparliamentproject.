package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.repository.ParliamentMemberRepository
import kotlinx.coroutines.launch

/**
 * This is a viewModel for fragmentMembers
 */
class FragmentMembersViewModel(application: Application) : AndroidViewModel(application) {

    //Initialising repository.
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeDao()
    )

    //Initialising variables for storing the party members of clicked party
    private var _clickedPartyMembers = MutableLiveData<List<ParliamentMembers>>()
    val clickedPartyMembers: LiveData<List<ParliamentMembers>> = _clickedPartyMembers
    private var clickedPartyName: String = ""

    /**
     * Introducing function for getting party members of clicked party.
     * @param party name of the party
     */
    fun getMembersByParty(party: String) {
        viewModelScope.launch {
            _clickedPartyMembers.value = parliamentMemberRepository.getMembersByParty(party)

        }
    }

}

/**
 * This Factory class helps to initialize FragmentMembersViewModel with application as
 * parameter (without it, we cannot have application context to create our AppDatabase)
 *
 *
 * Source: https://stackoverflow.com/questions/54419236/why-a-viewmodel-factory-is-needed-in-android
 */
class FragmentMembersViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentMembersViewModel::class.java)) {
            FragmentMembersViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}