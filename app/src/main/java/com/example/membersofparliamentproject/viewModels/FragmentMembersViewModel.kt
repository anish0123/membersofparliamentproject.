package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMemberRepository
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.fragments.FragmentMembers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * This is a viewModel for fragmentMembers
 */
class FragmentMembersViewModel(application: Application) : AndroidViewModel(application) {

    //Initialising repository.
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao()
    )

    //Initialising variables for storing the party members of clicked party
    private var _clickedPartyMembers = MutableLiveData<List<ParliamentMembers>>()
    val clickedPartyMembers: LiveData<List<ParliamentMembers>> = _clickedPartyMembers
    private var clickedPartyName: String = ""

    //Introducing function for getting party members of clicked party.
    fun getMembersByParty(party: String) {
        viewModelScope.launch {
            _clickedPartyMembers.value = parliamentMemberRepository.getMembersByParty(party)

        }
    }

}

//Introducing viewModel factory for using application context
class FragmentMembersViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentMembersViewModel::class.java)) {
            FragmentMembersViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}