package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliamentproject.MembersAdapter
import com.example.membersofparliamentproject.PartiesAdapter
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.communicator.Communicator
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.databinding.FragmentMembersBinding
import com.example.membersofparliamentproject.viewModels.FragmentMembersViewModel
import com.example.membersofparliamentproject.viewModels.FragmentMembersViewModelFactory
import com.example.membersofparliamentproject.viewModels.FragmentPartiesViewModel
import com.example.membersofparliamentproject.viewModels.FragmentPartiesViewModelFactory

/**
 * This Fragment is used to display list of party members in a recyclerView.
 *
 */

class FragmentMembers : Fragment() {
    private lateinit var clickedParty: String
    private lateinit var adapter: MembersAdapter
    private var _binding: FragmentMembersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FragmentMembersViewModel
    private lateinit var communicator: Communicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMembersBinding.inflate(inflater, container, false)

        //arguments and communicator for getting party name from fragment Parties.
        clickedParty = arguments?.getString("party").toString()
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("bundleKey")
            if (result != null) {
                Log.d("CLicked Party2", result)
                clickedParty = result
            }
            viewModel.getMembersByParty(clickedParty)
        }



        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewMembers.layoutManager = layoutManager
        binding.recyclerViewMembers.setHasFixedSize(true)
        //Initialising viewModel with viewModelProvider
        viewModel = ViewModelProvider(
            this,
            FragmentMembersViewModelFactory(requireActivity().application)
        )[FragmentMembersViewModel::class.java]
        Log.d("Clicked Party Members", viewModel.getMembersByParty(clickedParty).toString())

        //Starting the observer for livedata which is list of parties.
        val clickedPartyObserver = Observer<List<ParliamentMembers>> { partyMembers ->
            adapter = MembersAdapter(partyMembers)
            binding.recyclerViewMembers.adapter = adapter
            //Adding setOnClickListener so whenever the party is clicked it displays it's members in Fragment Members
            adapter.setonItemClickListener(object : MembersAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    findNavController().navigate(R.id.action_fragmentParties2_to_fragmentMembers2)
                }


            })
        }
        //Initialising observer
        viewModel.clickedPartyMembers.observe(viewLifecycleOwner,clickedPartyObserver)

    }
}