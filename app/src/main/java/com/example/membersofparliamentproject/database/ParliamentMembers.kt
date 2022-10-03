package com.example.membersofparliamentproject.database

import android.os.Parcelable
import android.view.ViewDebug.CapturedViewProperty
import androidx.room.*
import kotlinx.android.parcel.Parcelize
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

): Parcelable
