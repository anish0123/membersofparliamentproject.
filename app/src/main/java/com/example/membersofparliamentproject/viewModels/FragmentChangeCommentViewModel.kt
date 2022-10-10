package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMembersLikeAndComment
import com.example.membersofparliamentproject.repository.ParliamentMemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This is the viewModel for fragment changeComment
 */
class FragmentChangeCommentViewModel(application: Application): AndroidViewModel(application) {

    //Initialising repository.
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeDao())


    /**
     * Function to delete comment
     */
    fun deleteComment(comment: ParliamentMembersLikeAndComment) {
        viewModelScope.launch(Dispatchers.IO) {
            parliamentMemberRepository.deleteComment(comment)
        }
    }

    /**
     * Function to add comment
     */
    fun addComment(comment: ParliamentMembersLikeAndComment) {
        viewModelScope.launch(Dispatchers.IO) {
            parliamentMemberRepository.addComment(comment)
        }
    }




}

/**
 * This Factory class helps to initialize FragmentChangeComment with application as
 * parameter (without it, we cannot have application context to create your AppDatabase)
 *
 *
 * Source: https://stackoverflow.com/questions/54419236/why-a-viewmodel-factory-is-needed-in-android
 */
class FragmentChangeCommentViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentChangeCommentViewModel::class.java)) {
            FragmentChangeCommentViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}
