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
interface ParliamentMembersCommentDao {

    /**
     * Function to insert comment about a parliament member
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addComment(comment: ParliamentMembersComment)

    /**
     * Function to update comment
     */
      @Update
      suspend fun updateComment(comment: ParliamentMembersComment)

    /**
     * Function to delete comment
     */
    @Delete
    suspend fun deleteComment(comment: ParliamentMembersComment)

    /**
     * Function to get all the comments about all the parliament members
     */
    @Query("SELECT  * FROM ParliamentMembers_comment")
    suspend fun getAllComments(): List<ParliamentMembersComment>



    @Query("SELECT * FROM PARLIAMENTMEMBERS_COMMENT WHERE hetekaId = :hetekaId")
    suspend fun getCommentByHetekaId(hetekaId :Int) : List<ParliamentMembersComment>
}