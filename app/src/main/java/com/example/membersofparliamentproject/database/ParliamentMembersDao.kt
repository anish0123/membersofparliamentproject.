package com.example.membersofparliamentproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParliamentMembersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMember(member: ParliamentMembers)

    @Query("SELECT * FROM ParliamentMembers_table ORDER BY HetekaId ASC")
    fun readAllData(): LiveData<List<ParliamentMembers>>
}