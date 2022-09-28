package com.example.membersofparliamentproject

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PartiesAdapter(private val partiesList : List<String>) : RecyclerView.Adapter<PartiesAdapter.PartiesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        Log.d(TAG,"working here")
        return PartiesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PartiesViewHolder, position: Int) {
        val currentParty = partiesList[position]
        holder.partyHeading.text = currentParty

    }

    override fun getItemCount(): Int {
        return partiesList.size
    }

    class  PartiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val partyHeading: TextView = itemView.findViewById(R.id.item_title)
    }
    }
