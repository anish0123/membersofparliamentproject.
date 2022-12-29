package com.example.membersofparliamentproject.database

import androidx.room.Embedded
import androidx.room.Relation

/**
 * This class holds the connection between two data class: ParliamentMembers and ParliamentMembersLike
 * Source: https://developer.android.com/training/data-storage/room/relationships
 */
data class ParliamentMembersAndLike(
    @Embedded val parliamentMembers: ParliamentMembers,
    @Relation(
        parentColumn = "hetekaId",
        entityColumn = "hetekaId"
    )
    val parliamentMembersLike: ParliamentMembersLike
)
