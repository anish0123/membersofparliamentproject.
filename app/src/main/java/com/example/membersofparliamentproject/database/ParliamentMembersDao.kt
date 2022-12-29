package com.example.membersofparliamentproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This interface is created to get data from database about parliament members through the queries created in this interface.
 */
@Dao
interface ParliamentMembersDao {

    /**
     * Funtion to add members
     * @param member that needs to be added.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMember(member: ParliamentMembers)

    /**
     * This method is to insert all members from network to the database
     * @param memberList  list of members that needs to be added
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMembers(memberList: List<ParliamentMembers>)

    /**
     * This method can be used to get all the data of parliament members
     * @return get the list of all parliament members
     */

    @Query("SELECT * FROM ParliamentMembers_table ORDER BY HetekaId ASC")
    fun readAllData(): LiveData<List<ParliamentMembers>>

    /**
     * This method is used to get all parties of the parliament
     * @return get list of all parties
     */
    @Query("SELECT party FROM ParliamentMembers_table ")
    suspend fun getMemberParty(): List<String>

    /**
     * This method provides all the list of the members of the selected party
     * @param party name of the party whose value of the members need to be passed
     * @return list of members of the party
     */
    @Query("SELECT * FROM ParliamentMembers_table WHERE party LIKE :party")
    suspend fun getMembersByParty(party: String): List<ParliamentMembers>

}