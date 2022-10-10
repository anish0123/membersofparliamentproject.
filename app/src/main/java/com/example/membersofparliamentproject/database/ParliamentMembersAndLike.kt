package com.example.membersofparliamentproject.database

import androidx.room.Embedded
import androidx.room.Relation

data class ParliamentMembersAndLike(
    @Embedded val parliamentMembers: ParliamentMembers,
    @Relation(
        parentColumn = "hetekaId",
        entityColumn = "hetekaId"
    )
    val parliamentMembersLike: ParliamentMembersLike
)
