package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMemberRepository
import com.example.membersofparliamentproject.database.ParliamentMembersExtra
import com.example.membersofparliamentproject.database.ParliamentMembersLikeAndComment
import com.example.membersofparliamentproject.fragments.FragmentComment
import kotlinx.coroutines.launch

class FragmentCommentViewModel(application: Application) :AndroidViewModel(application) {

    //Introducing the repository
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao()
    )

    //Introducing livedata object
    private var _comment= MutableLiveData<List<ParliamentMembersLikeAndComment>>()
    val comment: LiveData<List<ParliamentMembersLikeAndComment>> = _comment

    //Function to save extraInfo to live data
    fun getAllComments() {
        viewModelScope.launch {
            _comment.value = parliamentMemberRepository.getAllComments()
        }
    }

    fun addComment(comment: ParliamentMembersLikeAndComment) {
        viewModelScope.launch {
            parliamentMemberRepository.addComment(comment)
        }
    }
    fun updateComment(comment: String, commentId :Int) {
        viewModelScope.launch {
            parliamentMemberRepository.updateComment(comment,commentId)
        }
    }
}

class FragmentCommentViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentCommentViewModel::class.java)) {
            FragmentCommentViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}