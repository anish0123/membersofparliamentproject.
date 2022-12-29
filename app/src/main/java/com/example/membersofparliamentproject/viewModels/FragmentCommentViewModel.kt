package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMembersLikeAndComment
import com.example.membersofparliamentproject.repository.ParliamentMemberRepository
import kotlinx.coroutines.launch

/**
 * This viewModel all the data that needs to be displayed in fragment Comment
 */

class FragmentCommentViewModel(application: Application) : AndroidViewModel(application) {

    //Introducing the repository
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeDao()
    )

    //Introducing livedata object
    private var _comment = MutableLiveData<List<ParliamentMembersLikeAndComment>?>()
    val comment: MutableLiveData<List<ParliamentMembersLikeAndComment>?> = _comment

    private var _commentByHetekaId = MutableLiveData<List<ParliamentMembersLikeAndComment>>()
    val commentByHetekaId: LiveData<List<ParliamentMembersLikeAndComment>> = _commentByHetekaId


    /**
     * Function to save extraInfo to live data
     */

    fun getAllComments() {
        viewModelScope.launch {
            _comment.value = parliamentMemberRepository.getAllComments()
        }
    }

    /**
     * This function is used for adding comments.
     * @param comment comment that needs to be added
     */
    fun addComment(comment: ParliamentMembersLikeAndComment) {
        viewModelScope.launch {
            parliamentMemberRepository.addComment(comment)
        }
    }


    /**
     * Function to get comment by hetekaId
     * @param hetekaId Id of the member
     */

    fun getCommentByHetekaId(hetekaId: Int) {
        viewModelScope.launch {
            _commentByHetekaId.value = parliamentMemberRepository.getCommentByHetekaId(hetekaId)
        }
    }


}

/**
 * This Factory class helps to initialize FragmentCommentViewModel with application as
 * parameter (without it, we cannot have application context to create our AppDatabase)
 * Source: https://stackoverflow.com/questions/54419236/why-a-viewmodel-factory-is-needed-in-android
 */
class FragmentCommentViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentCommentViewModel::class.java)) {
            FragmentCommentViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}