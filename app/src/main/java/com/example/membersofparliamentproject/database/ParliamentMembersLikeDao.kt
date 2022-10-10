package com.example.membersofparliamentproject.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This interface is created to take data from ParliamentMembersLike table.
 */
@Dao
interface ParliamentMembersLikeDao {

    /**
     * Function to insert like to the parliament Member
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLike(membersLike: ParliamentMembersLike)

    /**
     * Function to delete like to the parliament Member
     */
    @Delete
    suspend fun deleteLike(membersLike: ParliamentMembersLike)

    /**
     * Function to get all likes of all parliament Members
     */
    @Query("SELECT * FROM ParliamentMembersLike_table")
    suspend fun getAllLike() : List<ParliamentMembersLike>


}