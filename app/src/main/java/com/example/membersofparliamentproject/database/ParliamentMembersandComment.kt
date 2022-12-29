package com.example.membersofparliamentproject.database

import androidx.room.Embedded
import androidx.room.Relation

/**
 * This class is created for establishing relation between two database classes: parliament members and parliamentMembersLikeAndComment classes
 * Source: https://developer.android.com/training/data-storage/room/relationships
 */
data class ParliamentMembersAndComment(
    @Embedded val parliamentMembers: ParliamentMembers,
    @Relation(
        parentColumn = "hetekaId",
        entityColumn = "hetekaId"
    )
    val parliamentMembersLikeAndComment: ParliamentMembersLikeAndComment
)
