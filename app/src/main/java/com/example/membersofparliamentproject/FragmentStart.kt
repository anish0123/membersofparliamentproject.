package com.example.membersofparliamentproject

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
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.database.ParliamentMembersViewModel

//
class FragmentStart : Fragment() {
    private lateinit var parliamentMembersViewModel: ParliamentMembersViewModel
    private lateinit var viewModel: FragmentStartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        viewModel = ViewModelProvider(this)[FragmentStartViewModel::class.java]
        viewModel.getMembers()
        Log.d(ContentValues.TAG, "is it working $viewModel.getMembers().toString()")
        val button = view.findViewById<Button>(R.id.startBtn)
        parliamentMembersViewModel = ViewModelProvider(this)[ParliamentMembersViewModel::class.java]
        val newMember = ParliamentMembers(1,1,"Maharjan","Anish","Nepal",true,"")
        parliamentMembersViewModel.addMember(newMember)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentStart3_to_fragmentParties2)
        }
        return view
    }
}

