package com.example.membersofparliamentproject.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * This class is created to create a table in database for storing like and comment of parliament member
 */
@Parcelize
@Entity(tableName = "ParliamentMembers_comment")
data class ParliamentMembersLikeAndComment(
    @PrimaryKey(autoGenerate = false)
    val comment: String,
    val hetekaId: Int
) : Parcelable
