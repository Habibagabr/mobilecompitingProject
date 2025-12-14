package com.habiba.studysmart.data.dataSource.local.database

import android.util.Log
import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.data.model.SubjectIdNameModel
import com.habiba.studysmart.data.model.SubjectModel
import com.habiba.studysmart.data.model.SubjectWithTasksAndSessions
import com.habiba.studysmart.data.model.TaskModel
import com.habiba.studysmart.data.model.UserHomeDataModel
import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.domain.mapper.localMapper.toData
import com.habiba.studysmart.domain.model.SubjectDomainModel
import javax.inject.Inject

class DatabaseServices@Inject constructor(
    private val appDao: AppDao

): IDatabaseServices {

    // signup
    override suspend fun addUser(user: UserModel) {
        appDao.addNewUser(user)
    }


    override suspend fun getUserHomeData(userId: String): UserHomeDataModel {
        Log.d("home services", " ehna henaa 3")
        val data =  appDao.getUserData(userId)
        Log.d("home services", " $data")

        return data?:UserHomeDataModel(
            user = UserModel(
                userId = userId,
                userEmail = "",
                userPassword = "",
                userName = "",
                subjectCount = 0,
                studiedHour = 0,
                studyGoalHour = 0
            ),
            userDetails = emptyList()
        )

    }

    override suspend fun isUserExists(userId: String): Boolean {
        return appDao.isUserExists(userId)
    }

    override suspend fun addNewSubject(newSubjectData: SubjectModel) {
        appDao.addNewSubject(newSubjectData)
    }

    override suspend fun updateUser(updatedUser: UserModel) {
        appDao.updateUser(updatedUser)
    }

    override suspend fun getUserData(userOwnerId: String): UserHomeDataModel {
        val data =  appDao.getUserData(userOwnerId)
        Log.d("home services22", " $data")


        return data?:UserHomeDataModel(
            user = UserModel(
                userId = userOwnerId,
                userEmail = "",
                userPassword = "",
                userName = "",
                subjectCount = 0,
                studiedHour = 0,
                studyGoalHour = 0
            ),
            userDetails = emptyList()
        )
    }

    override suspend fun getSubjectDetails(subjectId: Int): SubjectWithTasksAndSessions {
        return appDao.getSubjectDetails(subjectId)
    }

    override suspend fun addNewTask(taskModel: TaskModel) {
        return appDao.addNewTask(taskModel)
    }

    override suspend fun getTaskDetails(taskId: Int): TaskModel {
        return appDao.getTaskDetails(taskId)
    }

    override suspend fun getUserHistorySession(userId: String): List<SessionModel>{
        return appDao.getUserHistorySession(userId)
    }

    override suspend fun getAllUserUniqueSubjects(userId: String): List<SubjectIdNameModel> {
        return appDao.getAllUserUniqueSubjects(userId)
    }

    override suspend fun saveSession(session: SessionModel) {
        appDao.addNewSession(session)
    }

    override suspend fun markTaskAsCompleted(taskId: Int) {
        appDao.markTaskAsCompleted(taskId)
    }


}
