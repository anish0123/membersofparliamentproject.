package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMemberRepository
import com.example.membersofparliamentproject.database.ParliamentMembers
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
    private var _clickedMember = MutableLiveData<ParliamentMembers>()
    val clickedMember: LiveData<ParliamentMembers> = _clickedMember


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