package com.example.membersofparliamentproject.database

import androidx.room.Embedded
import androidx.room.Relation

data class ParliamentMembersAndComment(
    @Embedded val parliamentMembers: ParliamentMembers,
    @Relation(
        parentColumn = "hetekaId",
        entityColumn = "hetekaId"
    )
    val parliamentMembersLikeAndComment: ParliamentMembersLikeAndComment
)
