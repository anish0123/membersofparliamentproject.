package com.example.membersofparliamentproject.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ParliamentMembers_comment")
data class ParliamentMembersLikeAndComment(
    @PrimaryKey(autoGenerate = true)
    val commentId: Int,
    val comment: String,
    val like: Boolean,
    val hetekaId: Int
):Parcelable
