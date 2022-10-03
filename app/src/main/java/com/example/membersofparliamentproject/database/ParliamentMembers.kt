package com.example.membersofparliamentproject.database

import android.view.ViewDebug.CapturedViewProperty
import androidx.room.*

@Entity(tableName = "ParliamentMembers_table")
data class ParliamentMembers(
    @PrimaryKey(autoGenerate = false)
    val hetekaId: Int,
    val seatNumber: Int,
     val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String

)
