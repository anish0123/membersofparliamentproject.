package com.example.membersofparliamentproject.database

import androidx.annotation.IntDef
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParliamentMembersExtraDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExtra(extra: ParliamentMembersExtra)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllExtra(extraList: List<ParliamentMembersExtra>)


}