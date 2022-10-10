package com.example.membersofparliamentproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliamentproject.R

/**
 * This class is adapter for recyclerView in fragment parties.
 */
class PartiesAdapter(private val partiesList: List<String>) :
    RecyclerView.Adapter<PartiesViewHolder>() {
    private lateinit var myListener: OnItemClickListener

    /**
     * Interface for adding the click listener
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    /**
     * getting the listener
     */
    fun setonItemClickListener(listener: OnItemClickListener) {
        myListener = listener

    }

    /**
     * Function to create the itemholder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return PartiesViewHolder(itemView, myListener)
    }

    /**
     * Function for binding the viewHolder
     */
    override fun onBindViewHolder(holder: PartiesViewHolder, position: Int) {
        val currentParty = partiesList[position]
        holder.partyHeading.text = currentParty

    }

    /**
     * Function for getting the total item count.
     */
    override fun getItemCount(): Int {
        return partiesList.size
    }
}

/**
 * This class is created as a viewHolder in the recyclerView in fragment parties
 */
class PartiesViewHolder(itemView: View, listener: PartiesAdapter.OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    val partyHeading: TextView = itemView.findViewById(R.id.item_title)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

}

