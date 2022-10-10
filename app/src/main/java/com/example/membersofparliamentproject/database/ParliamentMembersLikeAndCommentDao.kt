package com.example.membersofparliamentproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This interface is created to store and get data from database.
 */
@Dao
interface ParliamentMembersLikeAndCommentDao {

    /**
     * Function to insert comment about a parliament member
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComment(comment: ParliamentMembersLikeAndComment)

    /**
     * Function to get all the comments about all the parliament members
     */
    @Query("SELECT  * FROM ParliamentMembers_comment")
    suspend fun getAllComments(): List<ParliamentMembersLikeAndComment>

    /**
     * Function to update comment about a single parliament members.
     */
    @Query("UPDATE ParliamentMembers_comment SET comment = :comment WHERE commentId = :commentId")
    suspend fun updateComment(comment :String, commentId: Int)
}