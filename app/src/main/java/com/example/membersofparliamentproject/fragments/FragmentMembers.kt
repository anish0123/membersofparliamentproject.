package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.membersofparliamentproject.adapters.MembersAdapter
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.databinding.FragmentMembersBinding
import com.example.membersofparliamentproject.viewModels.FragmentMembersViewModel
import com.example.membersofparliamentproject.viewModels.FragmentMembersViewModelFactory

/**
 * This Fragment is used to display list of party members in a recyclerView.
 *
 */

class FragmentMembers : Fragment() {
    private var clickedParty: String = ""
    private lateinit var adapter: MembersAdapter
    private var _binding: FragmentMembersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FragmentMembersViewModel

    /**
     * It starts the lifecycle of fragment members and inflate the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMembersBinding.inflate(inflater, container, false)

        val view = binding.root
        return view
    }

    /**
     * It starts after onCreateView and initialise fragmentMembersViewModel.
     * It also gets list of partyMembers by using observer
     * It also passes clicked member to fragment Detail
     */
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
        //bundle for getting party name from fragment Parties.
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("bundleKey")
            if (result != null) {
                Log.d("CLicked Party2", result)
                clickedParty = result
            }
            //Calling function to get members of selectedParty
            viewModel.getMembersByParty(clickedParty)
        }

        //Starting the observer for livedata which is list of parties.
        val clickedPartyObserver = Observer<List<ParliamentMembers>> { partyMembers ->
            adapter = MembersAdapter(partyMembers)
            binding.recyclerViewMembers.adapter = adapter
            //Adding setOnClickListener so whenever the party is clicked it displays it's members in Fragment Members
            adapter.setonItemClickListener(object : MembersAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    //Passing the clicked member to detail fragment using navController
                    val memberResult = partyMembers[position]
                    val action =
                        FragmentMembersDirections.actionFragmentMembersToFragmentDetail(memberResult)
                    findNavController().navigate(action)
                    Toast.makeText(
                        context,
                        "${memberResult.firstname} ${memberResult.lastname} clicked",
                        Toast.LENGTH_SHORT
                    ).show()

                }


            })
        }
        //Initialising observer
        viewModel.clickedPartyMembers.observe(viewLifecycleOwner, clickedPartyObserver)

    }
}