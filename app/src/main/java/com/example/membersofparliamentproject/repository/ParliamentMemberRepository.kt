package com.example.membersofparliamentproject.repository

import com.example.membersofparliamentproject.database.*

class ParliamentMemberRepository(
    private val parliamentMembersDao: ParliamentMembersDao,
    private val parliamentMembersExtraDao: ParliamentMembersExtraDao,
    private val parliamentMembersLikeAndCommentDao: ParliamentMembersLikeAndCommentDao,
    private val parliamentMembersLikeDao: ParliamentMembersLikeDao
) {


    /**
     * Funtion for adding member of parliament
     */
    suspend fun addMember(member: ParliamentMembers) {
        parliamentMembersDao.addMember(member)
    }

    /**
     * Method for adding all the members
     */

    suspend fun addAllMembers(memberList: List<ParliamentMembers>) =
        parliamentMembersDao.addAllMembers(memberList)

    /**
     * This function is to call method from Dao to get all parties
     */
    suspend fun getMemberParty(): List<String> = parliamentMembersDao.getMemberParty()

    /**
     * This function is to add all Extra information about the members.
     */
    suspend fun addAllExtras(extraList: List<ParliamentMembersExtra>) =
        parliamentMembersExtraDao.addAllExtra(extraList)

    /**
     * This function is called for getting members of a selected party.
     */
    suspend fun getMembersByParty(party: String): List<ParliamentMembers> =
        parliamentMembersDao.getMembersByParty(party)

    /**
     * This function is called to get extra Info of a selected member
     */
    suspend fun getExtraInfo(hetekaId: Int): ParliamentMembersExtra =
        parliamentMembersExtraDao.getExtraInfo(hetekaId)

    /**
     * Function for getting all the extraInfo about parliament Members
     */
    suspend fun getAllExtraInfo(): List<ParliamentMembersExtra> =
        parliamentMembersExtraDao.getAllExtraInfo()

    /**
     * Function to get all the comments about the parliament Members
     */
    suspend fun getAllComments(): List<ParliamentMembersLikeAndComment> =
        parliamentMembersLikeAndCommentDao.getAllComments()

    /**
     * function to add comments about the members
     */

    suspend fun addComment(comment: ParliamentMembersLikeAndComment) =
        parliamentMembersLikeAndCommentDao.addComment(comment)


    /**
     * Function to get comment by HetekaId
     */
    suspend fun getCommentByHetekaId(hetekaId: Int): List<ParliamentMembersLikeAndComment> = parliamentMembersLikeAndCommentDao.getCommentByHetekaId(hetekaId)

    /**
     * Function to update comment
     */
    suspend fun updateComment(comment: ParliamentMembersLikeAndComment) = parliamentMembersLikeAndCommentDao.updateComment(comment)

    /**
     * Function to delete comment
     */
    suspend fun deleteComment(comment: ParliamentMembersLikeAndComment) = parliamentMembersLikeAndCommentDao.deleteComment(comment)

    /**
     * Function to add like/dislike to parliament Members
     */

    suspend fun addLike(like: ParliamentMembersLike) = parliamentMembersLikeDao.addLike(like)

    /**
     * Function to delete like/dislike to parliament Members
     */
    suspend fun deleteLike(like: ParliamentMembersLike) = parliamentMembersLikeDao.deleteLike(like)

    /**
     * Function to get all likes of all parliament members
     */
    suspend fun getAllLike() :List<ParliamentMembersLike> = parliamentMembersLikeDao.getAllLike()
}