package com.example.membersofparliamentproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliamentproject.MembersAdapter
import com.example.membersofparliamentproject.R
<<<<<<< HEAD

=======
>>>>>>> bd17234 (fixing while pushing the code to gitlab)

/**
 * This Fragment is used to display list of party members in a recyclerView.
 *
 */

class FragmentMembers : Fragment() {
    private lateinit var clickedParty : String
    private lateinit var adapter: MembersAdapter
    private lateinit var recyclerView: RecyclerView


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
        //adapter = MembersAdapter(partyMembers)
        recyclerView.adapter = adapter
        adapter.setonItemClickListener(object: MembersAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
            }

        })
    }


}