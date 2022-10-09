package com.example.membersofparliamentproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParliamentMembersLikeAndCommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComment(comment: ParliamentMembersLikeAndComment)

    @Query("SELECT  * FROM ParliamentMembers_comment")
    suspend fun getAllComments(): List<ParliamentMembersLikeAndComment>

    @Query("UPDATE ParliamentMembers_comment SET comment = :comment WHERE commentId = :commentId")
    suspend fun updateComment(comment :String, commentId: Int)
}