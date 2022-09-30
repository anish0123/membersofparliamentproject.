package com.example.membersofparliamentproject.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.viewModels.ParliamentMembersViewModel
import com.example.membersofparliamentproject.viewModels.FragmentStartViewModel
import com.example.membersofparliamentproject.viewModels.FragmentStartViewModelFactory

/**
 * This is a HOME fragment (showing intro and fetch data network)
 * 1 viewModel : FragmentStartViewModel (doing network fetching and saving to database)
 */
class FragmentStart : Fragment() {
    // - Comments: You should have 1 viewmodel per fragment only.
    private lateinit var viewModel: FragmentStartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        // Initialise FragmentStartViewModel
        viewModel = ViewModelProvider(this, FragmentStartViewModelFactory(requireActivity().application))[FragmentStartViewModel::class.java]

        viewModel.getMembers()
        viewModel.saveDataToDatabase()

//        Log.d(ContentValues.TAG, "is it working $viewModel.getMembers().toString()")
        val button = view.findViewById<Button>(R.id.startBtn)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentStart3_to_fragmentParties2)
        }
        return view
    }
}

