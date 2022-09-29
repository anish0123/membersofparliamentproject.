package com.example.membersofparliamentproject.database

import androidx.lifecycle.LiveData

class ParliamentMemberRepository(private val parliamentMembersDao: ParliamentMembersDao) {

    val readAllData: LiveData<List<ParliamentMembers>> = parliamentMembersDao.readAllData()

    suspend fun addMember(member: ParliamentMembers) {
        parliamentMembersDao.addMember(member)
    }

}