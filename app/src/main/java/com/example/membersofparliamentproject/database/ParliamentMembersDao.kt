package com.example.membersofparliamentproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParliamentMembersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMember(member: ParliamentMembers)

    //This method is to insert all members from network to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMembers(memberList:List<ParliamentMembers>)

    @Query("SELECT * FROM ParliamentMembers_table ORDER BY HetekaId ASC")
    fun readAllData(): LiveData<List<ParliamentMembers>>

    @Query("SELECT party FROM ParliamentMembers_table ")
    fun getMemberParty(): LiveData<List<String>>

    @Query("SELECT * FROM ParliamentMembers_table WHERE party LIKE :party")
    fun getMembersByParty(party: String): List<ParliamentMembers>
}