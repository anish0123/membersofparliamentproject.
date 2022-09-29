package com.example.membersofparliamentproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliamentproject.database.ParliamentMembers
import com.example.membersofparliamentproject.database.ParliamentMembersViewModel
import parliamentexercise.Parliament
import parliamentexercise.ParliamentMembersData


class FragmentParties : Fragment() {
    private lateinit var adapter: PartiesAdapter
    private lateinit var recyclerView: RecyclerView
    private var parties = Parliament(ParliamentMembersData.members).parties()
    private lateinit var parliamentMembersViewModel: ParliamentMembersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.recyclerViewParty)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = PartiesAdapter(parties)
        recyclerView.adapter = adapter
        adapter.setonItemClickListener(object: PartiesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                findNavController().navigate(R.id.action_fragmentParties2_to_fragmentMembers2)
            }

        })
    }
}