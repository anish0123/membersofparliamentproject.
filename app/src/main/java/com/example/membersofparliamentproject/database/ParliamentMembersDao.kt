package com.example.membersofparliamentproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This is interface for data class Parliament members.
 */
@Dao
interface ParliamentMembersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMember(member: ParliamentMembers)

    //This method is to insert all members from network to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMembers(memberList:List<ParliamentMembers>)

    //This method can be used to get all the data of parliament members
    @Query("SELECT * FROM ParliamentMembers_table ORDER BY HetekaId ASC")
    fun readAllData(): LiveData<List<ParliamentMembers>>

    //This method is used to get all parties of the parliament
    @Query("SELECT party FROM ParliamentMembers_table ")
    suspend fun getMemberParty(): List<String>

    //This method provides all the list of the members of the selected party
    @Query("SELECT * FROM ParliamentMembers_table WHERE party LIKE :party")
    fun getMembersByParty(party: String): List<ParliamentMembers>

}