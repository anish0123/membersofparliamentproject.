package com.example.membersofparliamentproject.database

import androidx.room.*

/**
 * This interface is created to get data from database about like and dislike on parliament members through the queries created in this interface.
 */
@Dao
interface ParliamentMembersLikeDao {

    /**
     * Function to insert like to the parliament Member
     * @param membersLike like/dislike to the member that needs to be added
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLike(membersLike: ParliamentMembersLike)

    /**
     * Function to delete like to the parliament Member
     * @param membersLike like/dislike to the member that needs to be deleted
     */
    @Delete
    suspend fun deleteLike(membersLike: ParliamentMembersLike)

    /**
     * Function to get all likes of all parliament Members
     * @return list of all likes/dislikes to all members
     */
    @Query("SELECT * FROM ParliamentMembersLike_table")
    suspend fun getAllLike(): List<ParliamentMembersLike>


}