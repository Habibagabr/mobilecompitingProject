package com.habiba.studysmart.domain.repository

import com.habiba.studysmart.data.model.UserHomeDataModel
import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel

interface IHomeRepository {
    suspend fun addNewUser(user: UserModel)
    suspend fun getUserData(userId:String): UserHomeDataDomainModel

    //log out

    suspend fun addSubjectAndUpdateUser(
        subjectDomain: SubjectDomainModel
    ): UserHomeDataModel

    suspend fun markTaskCompleted(taskId: Int)

}