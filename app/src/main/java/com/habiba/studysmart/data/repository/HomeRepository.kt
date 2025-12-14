package com.habiba.studysmart.data.repository

import android.util.Log
import com.habiba.studysmart.data.dataSource.local.database.IDatabaseServices
import com.habiba.studysmart.data.model.UserHomeDataModel
import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.data.utils.localMappers.toDomain
import com.habiba.studysmart.domain.mapper.localMapper.toData
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel
import com.habiba.studysmart.domain.repository.IHomeRepository
import javax.inject.Inject

class HomeRepository@Inject constructor(
    private val databaseServices: IDatabaseServices
): IHomeRepository {

    override suspend fun addNewUser(user: UserModel ) {
        databaseServices.addUser(user)
    }

    override suspend fun getUserData(userId: String): UserHomeDataDomainModel {
        Log.d("home repo","ehnaa hennaaa")
        val data = databaseServices.getUserHomeData(userId)
        Log.d("home repo","ehnaa hennaaa2")

        return data.toDomain()
    }

    override suspend fun addSubjectAndUpdateUser(
        subjectDomain: SubjectDomainModel
    ): UserHomeDataModel {

        // 1) Fetch current user data
        val userData = databaseServices.getUserData(subjectDomain.userOwnerId)

        // 2) Build updated user
        val updatedUser = userData.user.copy(
            subjectCount = userData.user.subjectCount + 1,
            studyGoalHour = userData.user.studyGoalHour + subjectDomain.goalHours
        )

        // 3) Convert Domain → Data
        val newSubjectData = subjectDomain.toData()

        // 4) Save new subject
        databaseServices.addNewSubject(newSubjectData)

        // 5) Save updated user
        databaseServices.updateUser(updatedUser)

        // 6) Reload full user data with relations applied
        return databaseServices.getUserData(subjectDomain.userOwnerId)
    }

    override suspend fun markTaskCompleted(taskId: Int) {
        databaseServices.markTaskAsCompleted(taskId)
    }


}