package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.adapters.PartiesAdapter
import com.example.membersofparliamentproject.databinding.FragmentPartiesBinding
import com.example.membersofparliamentproject.viewModels.FragmentPartiesViewModel
import com.example.membersofparliamentproject.viewModels.FragmentPartiesViewModelFactory

/**
 * This is Party Fragment which displays party lists on recyclerView
 * Source: https://developer.android.com/develop/ui/views/layout/recyclerview
 */

class FragmentParties : Fragment() {
    private lateinit var adapter: PartiesAdapter
    private lateinit var viewModel: FragmentPartiesViewModel
    private var _binding: FragmentPartiesBinding? = null
    private val binding get() = _binding!!

    /**
     * Lifecycle of fragment parties starts over here
     * @param inflater and container
     * @return view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPartiesBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    /**
     * It starts after onCreateView.
     * It initialises fragmentPartiesViewModel and gets a list of parties through observer
     *  @param view and savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)

        //Initialising recyclerView, its adapter and viewModel
        binding.recyclerViewParty.layoutManager = layoutManager
        binding.recyclerViewParty.setHasFixedSize(true)
        viewModel = ViewModelProvider(
            this,
            FragmentPartiesViewModelFactory(requireActivity().application)
        )[FragmentPartiesViewModel::class.java]
        viewModel.getMemberParty()
        //Starting the observer for livedata which is list of parties.
        val partiesObserver = Observer<List<String>> { parties ->
            adapter = PartiesAdapter(parties.distinct().sorted())
            binding.recyclerViewParty.adapter = adapter
            //Adding setOnClickListener so whenever the party is clicked it displays it's members in Fragment Members
            adapter.setonItemClickListener(object : PartiesAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val result = parties.distinct().sorted()[position]
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    //Using the nav controller to navigate
                    findNavController().navigate(R.id.action_fragmentParties2_to_fragmentMembers2)
                    Toast.makeText(context, "$result clicked", Toast.LENGTH_SHORT).show()
                }

            })
        }
        //Observer start.
        viewModel.listedParties.observe(viewLifecycleOwner, partiesObserver)


    }
}