package com.example.membersofparliamentproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliamentproject.R

class PartiesAdapter(private val partiesList: List<String>) :
    RecyclerView.Adapter<PartiesViewHolder>() {
    private lateinit var myListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: OnItemClickListener) {
        myListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return PartiesViewHolder(itemView, myListener)
    }

    override fun onBindViewHolder(holder: PartiesViewHolder, position: Int) {
        val currentParty = partiesList[position]
        holder.partyHeading.text = currentParty

    }

    override fun getItemCount(): Int {
        return partiesList.size
    }
}

class PartiesViewHolder(itemView: View, listener: PartiesAdapter.OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    val partyHeading: TextView = itemView.findViewById(R.id.item_title)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

}

