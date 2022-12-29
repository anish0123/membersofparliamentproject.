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
     * @param member memberthat needs to be added in the database
     */
    suspend fun addMember(member: ParliamentMembers) {
        parliamentMembersDao.addMember(member)
    }

    /**
     * Method for adding all the members
     * @param memberList list of parliament members
     */

    suspend fun addAllMembers(memberList: List<ParliamentMembers>) =
        parliamentMembersDao.addAllMembers(memberList)

    /**
     * This function is to call method from Dao to get all parties
     * @return get list of the parties
     */
    suspend fun getMemberParty(): List<String> = parliamentMembersDao.getMemberParty()

    /**
     * This function is to add all Extra information about the members.
     * @param extraList list of extra details about the members
     */
    suspend fun addAllExtras(extraList: List<ParliamentMembersExtra>) =
        parliamentMembersExtraDao.addAllExtra(extraList)

    /**
     * This function is called for getting members of a selected party.
     * @param party party name for passing the members of those parties
     * @return list of the parliament members
     */
    suspend fun getMembersByParty(party: String): List<ParliamentMembers> =
        parliamentMembersDao.getMembersByParty(party)

    /**
     * This function is called to get extra Info of a selected member
     * @param hetekaId insert hetekaId of selected member whose extra info is needed
     * @return extra detail of the selected member
     */
    suspend fun getExtraInfo(hetekaId: Int): ParliamentMembersExtra =
        parliamentMembersExtraDao.getExtraInfo(hetekaId)

    /**
     * Function for getting all the extraInfo about parliament Members
     * @return get list of all the extra details about all the members
     */
    suspend fun getAllExtraInfo(): List<ParliamentMembersExtra> =
        parliamentMembersExtraDao.getAllExtraInfo()

    /**
     * Function to get all the comments about the parliament Members
     * @return get list of all comments on all the members
     */
    suspend fun getAllComments(): List<ParliamentMembersLikeAndComment> =
        parliamentMembersLikeAndCommentDao.getAllComments()

    /**
     * function to add comments about the members
     * @param comment that needs to be inserted
     */

    suspend fun addComment(comment: ParliamentMembersLikeAndComment) =
        parliamentMembersLikeAndCommentDao.addComment(comment)


    /**
     * Function to get comment by HetekaId
     * @param hetekaId whose comment needs to be returned
     * @return list of selected parliament members comments.
     */
    suspend fun getCommentByHetekaId(hetekaId: Int): List<ParliamentMembersLikeAndComment> =
        parliamentMembersLikeAndCommentDao.getCommentByHetekaId(hetekaId)

    /**
     * Function to update comment
     * @param comment that needs to be updated
     */
    suspend fun updateComment(comment: ParliamentMembersLikeAndComment) =
        parliamentMembersLikeAndCommentDao.updateComment(comment)

    /**
     * Function to delete comment
     * @param comment about a member that needs to be deleted.
     */
    suspend fun deleteComment(comment: ParliamentMembersLikeAndComment) =
        parliamentMembersLikeAndCommentDao.deleteComment(comment)

    /**
     * Function to add like/dislike to parliament Members
     * @param like like/dislike to be added in the database
     */

    suspend fun addLike(like: ParliamentMembersLike) = parliamentMembersLikeDao.addLike(like)

    /**
     * Function to delete like/dislike to parliament Members
     * @param like like/dislike of a member to be deleted
     */
    suspend fun deleteLike(like: ParliamentMembersLike) = parliamentMembersLikeDao.deleteLike(like)

    /**
     * Function to get all likes of all parliament members
     * @return list of all likes/ dislikes for all parliament members
     */
    suspend fun getAllLike(): List<ParliamentMembersLike> = parliamentMembersLikeDao.getAllLike()
}