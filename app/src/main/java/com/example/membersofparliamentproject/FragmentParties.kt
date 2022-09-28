package com.example.membersofparliamentproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import parliamentexercise.Parliament
import parliamentexercise.ParliamentMembersData


class FragmentParties : Fragment() {
    private lateinit var adapter: PartiesAdapter
    private lateinit var recyclerView: RecyclerView
    private var parties = Parliament(ParliamentMembersData.members).parties()

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
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = PartiesAdapter(parties)
        recyclerView.adapter = adapter
    }
}