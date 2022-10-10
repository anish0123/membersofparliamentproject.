package com.example.membersofparliamentproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This interface if for data class of extra details of parliament members
 */
@Dao
interface ParliamentMembersExtraDao {

    /**
     * This method is used for adding extra details about parliament members
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExtra(extra: ParliamentMembersExtra)

    /**
     * This method is used to all list of extra details about parliament members
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllExtra(extraList: List<ParliamentMembersExtra>)

    /**
     * This method is used to get extra info of a parliament member using hetekaId
     */
    @Query("SELECT * FROM extra_table WHERE hetekaId = :hetekaId")
    suspend fun getExtraInfo(hetekaId: Int): ParliamentMembersExtra

    /**
     * Funtion to get all extraInfo about members
     */
    @Query("SELECT * FROM extra_table")
    suspend fun getAllExtraInfo(): List<ParliamentMembersExtra>


}