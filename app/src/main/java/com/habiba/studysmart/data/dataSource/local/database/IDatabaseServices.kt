package com.habiba.studysmart.data.dataSource.local.database


import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.data.model.SubjectIdNameModel
import com.habiba.studysmart.data.model.SubjectModel
import com.habiba.studysmart.data.model.SubjectWithTasksAndSessions
import com.habiba.studysmart.data.model.TaskModel
import com.habiba.studysmart.data.model.UserHomeDataModel
import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.domain.model.SubjectDomainModel

interface IDatabaseServices {

    // ---------------- User ----------------
    suspend fun addUser(user: UserModel)

    suspend fun getUserHomeData(userId: String): UserHomeDataModel

    suspend fun isUserExists(userId: String): Boolean


    // ---------------- Subject ----------------

    suspend fun addNewSubject(newSubjectData: SubjectModel)
    suspend fun updateUser(updatedUser: UserModel)
    suspend fun getUserData(userOwnerId: String): UserHomeDataModel

    suspend fun getSubjectDetails(subjectId:Int): SubjectWithTasksAndSessions
    // ---------------- Task ----------------
    suspend fun addNewTask(taskModel: TaskModel)
    suspend fun getTaskDetails(taskId: Int): TaskModel
    suspend fun markTaskAsCompleted(taskId: Int)


    //----------------- Sessions -------------
    suspend fun getUserHistorySession(userId:String): List<SessionModel>
    suspend fun getAllUserUniqueSubjects(userId:String): List<SubjectIdNameModel>
    suspend fun saveSession(session: SessionModel)
}
