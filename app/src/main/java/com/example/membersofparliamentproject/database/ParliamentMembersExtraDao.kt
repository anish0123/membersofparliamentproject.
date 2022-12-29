package com.example.membersofparliamentproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This interface is created to get data from database about extra details about parliament members through the queries created in this interface.
 */
@Dao
interface ParliamentMembersExtraDao {

    /**
     * This method is used for adding extra details about parliament members
     * @param extra extra details about a member that needs to be added
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExtra(extra: ParliamentMembersExtra)

    /**
     * This method is used to all list of extra details about parliament members
     * @param extraList list of extra details about members that needs to be added
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllExtra(extraList: List<ParliamentMembersExtra>)

    /**
     * This method is used to get extra info of a parliament member using hetekaId
     * @param hetekaId Id of the member whose value of extra details needs to passed
     * @return extra details about the member
     */
    @Query("SELECT * FROM extra_table WHERE hetekaId = :hetekaId")
    suspend fun getExtraInfo(hetekaId: Int): ParliamentMembersExtra

    /**
     * Funtion to get all extraInfo about members
     * @return pass list of extra details of all the members
     */
    @Query("SELECT * FROM extra_table")
    suspend fun getAllExtraInfo(): List<ParliamentMembersExtra>


}