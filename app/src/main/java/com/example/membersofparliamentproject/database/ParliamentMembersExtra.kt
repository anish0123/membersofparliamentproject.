package com.example.membersofparliamentproject.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Extra_table", foreignKeys = [ForeignKey(entity = ParliamentMembers::class,
    parentColumns = arrayOf("hetekaId"),
    childColumns = arrayOf("hetekaId"),
    onDelete = ForeignKey.CASCADE)]
)
data class ParliamentMembersExtra(
    @PrimaryKey(autoGenerate = true)
    val extraId: Int,
    val hetekaId: Int,
    val twitter: String,
    val bornYear: Int,
    val constituency: String
)