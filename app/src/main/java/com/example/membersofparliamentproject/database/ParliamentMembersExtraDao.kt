package com.example.membersofparliamentproject.database

import androidx.annotation.IntDef
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This interface if for data class of extra details of parliament members
 */
@Dao
interface ParliamentMembersExtraDao {

    //This method is used for adding extra details about parliament members
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExtra(extra: ParliamentMembersExtra)

    //This method is used to all list of extra details about parliament members
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllExtra(extraList: List<ParliamentMembersExtra>)


}