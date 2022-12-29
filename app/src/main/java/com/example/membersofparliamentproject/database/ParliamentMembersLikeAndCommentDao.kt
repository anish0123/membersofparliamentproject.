package com.example.membersofparliamentproject.database

import androidx.room.*

/**
 * This interface is created to get data from database about comments on parliament members through the queries created in this interface.
 */
@Dao
interface ParliamentMembersLikeAndCommentDao {

    /**
     * Function to insert comment about a parliament member
     * @param comment comment that needs to be added in the database
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addComment(comment: ParliamentMembersLikeAndComment)

    /**
     * Function to update comment
     * @param comment comment that needs to be updated
     */
    @Update
    suspend fun updateComment(comment: ParliamentMembersLikeAndComment)

    /**
     * Function to delete comment
     * @param comment comment that needs to be deleted
     */
    @Delete
    suspend fun deleteComment(comment: ParliamentMembersLikeAndComment)

    /**
     * Function to get all the comments about all the parliament members
     * @return list of all the comments about all the members
     */
    @Query("SELECT  * FROM ParliamentMembers_comment")
    suspend fun getAllComments(): List<ParliamentMembersLikeAndComment>


    /**
     * Function that returns comments according to hetekaID
     * @param hetekaId Id of the member whose comments needs to be passed
     * @return list of comments of selected member
     */
    @Query("SELECT * FROM PARLIAMENTMEMBERS_COMMENT WHERE hetekaId = :hetekaId")
    suspend fun getCommentByHetekaId(hetekaId: Int): List<ParliamentMembersLikeAndComment>
}