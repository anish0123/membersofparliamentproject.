package com.example.membersofparliamentproject.database

import androidx.lifecycle.LiveData

class ParliamentMemberRepository(private val parliamentMembersDao: ParliamentMembersDao) {

    val readAllData: LiveData<List<ParliamentMembers>> = parliamentMembersDao.readAllData()

    suspend fun addMember(member: ParliamentMembers) {
        parliamentMembersDao.addMember(member)
    }
    // This is to call method from Dao to add all members
    suspend fun addAllMembers(memberList: List<ParliamentMembers>) = parliamentMembersDao.addAllMembers(memberList)

    fun getMemberParty(): LiveData<List<String>> = parliamentMembersDao.getMemberParty()
}