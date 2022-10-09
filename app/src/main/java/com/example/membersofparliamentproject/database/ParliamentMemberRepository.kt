package com.example.membersofparliamentproject.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ParliamentMemberRepository(
    private val parliamentMembersDao: ParliamentMembersDao,
    private val parliamentMembersExtraDao: ParliamentMembersExtraDao,
    private val parliamentMembersLikeAndCommentDao: ParliamentMembersLikeAndCommentDao
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
    suspend fun getExtraInfo(hetekaId: Int): ParliamentMembersExtra = parliamentMembersExtraDao.getExtraInfo(hetekaId)

    /**
     * Function for getting all the extraInfo about parliament Members
     */
    suspend fun getAllExtraInfo(): List<ParliamentMembersExtra> = parliamentMembersExtraDao.getAllExtraInfo()

    /**
     * Function to get all the comments about the parliament Members
     */
    suspend fun getAllComments(): List<ParliamentMembersLikeAndComment> = parliamentMembersLikeAndCommentDao.getAllComments()

    /**
     * function to add comments about the members
     */

    suspend fun addComment(comment: ParliamentMembersLikeAndComment) = parliamentMembersLikeAndCommentDao.addComment(comment)

    /**
     * Function to update comments
     */
    suspend fun updateComment(comment :String, commentId :Int) = parliamentMembersLikeAndCommentDao.updateComment(comment,commentId)

}