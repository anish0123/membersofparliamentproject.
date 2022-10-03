package com.example.membersofparliamentproject.database

import androidx.room.Embedded
import androidx.room.Relation

/**
 * This data class creates the connection between two tables of the database
 */
data class ParliamentMembersAndExtra(
    @Embedded val parliamentMembers: ParliamentMembers,
    @Relation(
        parentColumn = "hetekaId",
        entityColumn = "hetekaId"
    )
    val parliamentMembersExtra: ParliamentMembersExtra
)
