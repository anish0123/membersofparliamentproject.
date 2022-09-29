package com.example.membersofparliamentproject

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import parliamentexercise.Parliament
import parliamentexercise.ParliamentMembersData


class FragmentMembers : Fragment() {
    private lateinit var clickedParty : String
    private lateinit var adapter: MembersAdapter
    private lateinit var recyclerView: RecyclerView
    private var partyMembers = Parliament(ParliamentMembersData.members).partyMembers(clickedParty)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_members, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.recyclerViewMembers)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MembersAdapter(partyMembers)
        recyclerView.adapter = adapter
        adapter.setonItemClickListener(object: MembersAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
            }

        })
    }


}