package com.example.membersofparliamentproject.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Extra_table")
data class ParliamentMembersExtra(
    @PrimaryKey(autoGenerate = false)
    val bornYear: Int,
    val hetekaId: Int,
    val constituency: String,
    val twitter: String
)