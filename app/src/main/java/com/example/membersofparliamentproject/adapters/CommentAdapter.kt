package com.example.membersofparliamentproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliamentproject.R
import com.example.membersofparliamentproject.database.ParliamentMembersLikeAndComment

/**
 * This class is the adapter for recyclerView in the comment fragment.
 */
class CommentAdapter(private val commentList: List<ParliamentMembersLikeAndComment>) :
    RecyclerView.Adapter<CommentViewHolder>() {
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return CommentViewHolder(itemView, myListener)
    }


    /**
     * Function for getting the total item count.
     */
    override fun getItemCount(): Int {
        return commentList.size
    }

    /**
     * Function for binding the viewHolder
     */
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentMember = commentList[position]
        (currentMember.comment).also { holder.commentHeading.text = it }
    }


}

/**
 * This class is created as a viewholder in the recyclerView in fragment members
 */

class CommentViewHolder(itemView: View, listener: CommentAdapter.OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    val commentHeading: TextView = itemView.findViewById(R.id.item_title)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}
