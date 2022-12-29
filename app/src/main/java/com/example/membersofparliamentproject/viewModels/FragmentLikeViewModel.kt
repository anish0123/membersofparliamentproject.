package com.example.membersofparliamentproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMembersLike
import com.example.membersofparliamentproject.repository.ParliamentMemberRepository
import kotlinx.coroutines.launch

/**
 * This is the viewModel for Fragment Like
 */
class FragmentLikeViewModel(application: Application) : AndroidViewModel(application) {

    //Initialising repository.
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeDao()
    )

    //Introducing liveData object
    private var _like = MutableLiveData<List<ParliamentMembersLike>>()
    val like: LiveData<List<ParliamentMembersLike>> = _like

    /**
     * Function to get all likes/dislikes from database
     */
    fun getAllLike() {
        viewModelScope.launch {
            _like.value = parliamentMemberRepository.getAllLike()
        }
    }

    /**
     * Function to add like/dislike to a member
     * @param like like/dislike to the member
     */

    fun addLike(like: ParliamentMembersLike) {
        viewModelScope.launch {
            parliamentMemberRepository.addLike(like)
        }
    }

    /**
     * Function to delete like/dislike to a members
     * @param like like/dislike to the member
     */
    fun deleteLike(like: ParliamentMembersLike) {
        viewModelScope.launch {
            parliamentMemberRepository.deleteLike(like)
        }
    }
}

/**
 * This Factory class helps to initialize FragmentLikeViewModel with application as
 * parameter (without it, we cannot have application context to create our AppDatabase)
 * Source: https://stackoverflow.com/questions/54419236/why-a-viewmodel-factory-is-needed-in-android
 */
class FragmentLikeViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentLikeViewModel::class.java)) {
            FragmentLikeViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}
