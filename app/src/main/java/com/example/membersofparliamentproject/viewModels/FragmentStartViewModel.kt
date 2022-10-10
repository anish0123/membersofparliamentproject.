package com.example.membersofparliamentproject.viewModels

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.membersofparliamentproject.database.AppDataBase
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.database.ParliamentMembersExtra
import com.example.membersofparliamentproject.network.ParliamentMemberApi
import com.example.membersofparliamentproject.repository.ParliamentMemberRepository
import kotlinx.coroutines.launch

/**
 * This ViewModel is to fetch data from network
 */
class FragmentStartViewModel(application: Application) : AndroidViewModel(application) {

    private val _listedMembers = MutableLiveData<List<ParliamentMembers>>()
    val listMembers: LiveData<List<ParliamentMembers>> = _listedMembers

    private val _listedExtras = MutableLiveData<List<ParliamentMembersExtra>>()
    val listedExtras: LiveData<List<ParliamentMembersExtra>> = _listedExtras

    // Initialise repository
    private val parliamentMemberRepository = ParliamentMemberRepository(
        AppDataBase.getDatabase(application).parliamentMembersDao(),
        AppDataBase.getDatabase(application).parliamentMembersExtraDao(),
        AppDataBase.getDatabase(application).parliamentMembersLikeAndCommentDao()
    )

    /**
     * Function for fetching the details of parliament members from network
     */
    //Fetching the data from network
    fun getMembers() {
        viewModelScope.launch {
            try {
                _listedMembers.value =
                    ParliamentMemberApi.retrofitService.getParliamentMembersList()
                Log.d("FETCH", "Fetching successfully")
            } catch (e: java.lang.Exception) {
                Log.d(ContentValues.TAG, "no luck in getting members: $e")
            }
        }
    }

    /**
     * Function to save data from network to database
     */

    fun saveDataToDatabase() {
        val obtainedList: List<ParliamentMembers>? = _listedMembers.value
        //In the case that value of MutableLiveData is not nul (success from network fetching)
        if (obtainedList != null) {
            viewModelScope.launch {
                parliamentMemberRepository.addAllMembers(obtainedList)
            }
        }

    }

    /**
     * Function for fetching the extra details of parliament members from network
     */
    fun getExtras() {
        viewModelScope.launch {
            try {
                _listedExtras.value =
                    ParliamentMemberApi.retrofitService.getParliamentMembersExtra()
                Log.d("NETWORK", "Fetching successfully")
            } catch (e: java.lang.Exception) {
                Log.d("NETWORK", "no luck in getting members: $e")
            }
        }
    }

    /**
     * Function to save extra details from network to database
     */
    fun saveExtraToDatabase() {
        val obtainedExtraList: List<ParliamentMembersExtra>? = _listedExtras.value
        Log.d(TAG, obtainedExtraList?.size.toString())
        if (obtainedExtraList != null) {
            viewModelScope.launch {
                parliamentMemberRepository.addAllExtras(obtainedExtraList)
                Log.d("EXTRALIST", "extralist obtained")
            }
        }

    }


}

/**
 * This Factory class helps to initialize FragmentStartViewModel with application as
 * parameter (without it, you cannot have application context to create your AppDatabase)
 *
 *
 * Source: https://stackoverflow.com/questions/54419236/why-a-viewmodel-factory-is-needed-in-android
 */
class FragmentStartViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FragmentStartViewModel::class.java)) {
            FragmentStartViewModel(this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}