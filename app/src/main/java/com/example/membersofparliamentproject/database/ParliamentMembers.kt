package com.example.membersofparliamentproject.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * This is a data class that holds details of parliament members
 */
@Parcelize
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

) : Parcelable
