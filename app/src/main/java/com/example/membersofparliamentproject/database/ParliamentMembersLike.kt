package com.example.membersofparliamentproject.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This data class holds data for storing like for members of parliament
 */
@Entity(tableName = "ParliamentMembersLike_table")
data class ParliamentMembersLike(
    @PrimaryKey(autoGenerate = true)
    val likeId: Int,
    val like: Boolean,
    val hetekaId: Int

) {
    constructor(like :Boolean, hetekaId: Int) : this(0,like,hetekaId)
}
