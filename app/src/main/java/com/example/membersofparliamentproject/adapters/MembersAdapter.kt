package com.example.membersofparliamentproject

import android.content.ContentValues
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliamentproject.database.ParliamentMembers


class MembersAdapter (private val partyMembers: List<ParliamentMembers>) :
    RecyclerView.Adapter<MemberViewHolder>() {
    private lateinit var myListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: OnItemClickListener) {
        myListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return MemberViewHolder(itemView, myListener)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val currentMember = partyMembers[position]
        val firstName = currentMember.firstname
        val lastName = currentMember.lastname
        ("$firstName $lastName").toString().also { holder.partyHeading.text = it }
    }

    override fun getItemCount(): Int {
        return partyMembers.size
    }

}

class MemberViewHolder (itemView: View, listener: MembersAdapter.OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    val partyHeading: TextView = itemView.findViewById(R.id.item_title)
    init {
        itemView.setOnClickListener{
            listener.onItemClick(adapterPosition)
        }
    }
}

