package com.example.membersofparliamentproject.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * This interface is created to store and get data from database.
 */
@Dao
interface ParliamentMembersLikeAndCommentDao {

    /**
     * Function to insert comment about a parliament member
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addComment(comment: ParliamentMembersLikeAndComment)

    /**
     * Function to update comment
     */
      @Update
      suspend fun updateComment(comment: ParliamentMembersLikeAndComment)

    /**
     * Function to delete comment
     */
    @Delete
    suspend fun deleteComment(comment: ParliamentMembersLikeAndComment)

    /**
     * Function to get all the comments about all the parliament members
     */
    @Query("SELECT  * FROM ParliamentMembers_comment")
    suspend fun getAllComments(): List<ParliamentMembersLikeAndComment>



    @Query("SELECT * FROM PARLIAMENTMEMBERS_COMMENT WHERE hetekaId = :hetekaId")
    suspend fun getCommentByHetekaId(hetekaId :Int) : List<ParliamentMembersLikeAndComment>
}