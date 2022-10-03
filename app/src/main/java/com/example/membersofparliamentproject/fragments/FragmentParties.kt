package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliamentproject.PartiesAdapter
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.communicator.Communicator
import com.example.membersofparliamentproject.databinding.FragmentPartiesBinding
import com.example.membersofparliamentproject.viewModels.FragmentPartiesViewModel
import com.example.membersofparliamentproject.viewModels.FragmentPartiesViewModelFactory
import com.example.membersofparliamentproject.viewModels.FragmentStartViewModelFactory
import com.example.membersofparliamentproject.viewModels.ParliamentMembersViewModel

/**
 * This is Party Fragment which displays party lists on recyclerView
 */

class FragmentParties : Fragment() {
    private lateinit var communicator: Communicator
    private lateinit var adapter: PartiesAdapter
    private lateinit var viewModel: FragmentPartiesViewModel
    private var _binding: FragmentPartiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPartiesBinding.inflate(inflater, container, false)
        communicator = activity as Communicator
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)

        //Initalising recyclerView and its adapter
        binding.recyclerViewParty.layoutManager = layoutManager
        binding.recyclerViewParty.setHasFixedSize(true)
        viewModel = ViewModelProvider(this,
            FragmentPartiesViewModelFactory(requireActivity().application)
        )[FragmentPartiesViewModel::class.java]
        //Starting the observer for livedata which is list of parties.
        val partiesObserver = Observer<List<String>> {parties ->
            adapter = PartiesAdapter(parties.distinct().sorted())
            binding.recyclerViewParty.adapter = adapter
            //Adding setOnClickListener so whenever the party is clicked it displays it's members in Fragment Members
            adapter.setonItemClickListener(object: PartiesAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    //communicator.passParty(parties[position])
                    val result = parties[position]
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    Log.d("Clicked Party1", parties[position])
                    findNavController().navigate(R.id.action_fragmentParties2_to_fragmentMembers2)
                }

            })
        }
        //Observer start.
        viewModel.listedParties.observe(viewLifecycleOwner, partiesObserver)

    }
}