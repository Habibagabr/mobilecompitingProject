package com.habiba.studysmart.data.dataSource.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.data.model.SubjectIdNameModel
import com.habiba.studysmart.data.model.SubjectModel
import com.habiba.studysmart.data.model.SubjectWithTasksAndSessions
import com.habiba.studysmart.data.model.TaskModel
import com.habiba.studysmart.data.model.UserHomeDataModel
import com.habiba.studysmart.data.model.UserModel

@Dao
interface AppDao {

     // user functionalities

     // in case of signup
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addNewUser(user: UserModel)

     @Query("""
    SELECT EXISTS(
        SELECT 1 
        FROM user_table 
        WHERE userId = :userId
    )
""")
     suspend fun isUserExists(userId: String): Boolean



     //////////////////////////////////////////////////////////////////////////////

     // subject functionalities

     @Transaction
     @Query("SELECT * FROM user_table WHERE userId = :userId")
     suspend fun getUserData(userId: String): UserHomeDataModel?


     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addNewSubject(subject: SubjectModel)

     @Update
     suspend fun updateUser(user: UserModel)


     @Query("select * from subject_table where subjectId = :subjectId")
     suspend fun getSubjectDetails(subjectId:Int): SubjectWithTasksAndSessions


     //////////////////////////////////////////////////////////////////////////////////

     // tasks functionalities
     @Query ("select * from task_table where taskId = :taskId")
     suspend fun  getTaskDetails(taskId:Int): TaskModel

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addNewTask(task: TaskModel)

     @Query("""
    UPDATE task_table
    SET isCompleted = 1
    WHERE taskId = :taskId
""")
     suspend fun markTaskAsCompleted(taskId: Int)


     //////////////////////////////////////////////////////////
     // session functionalities
     @Query("""
    SELECT s.*
    FROM study_session_table s
    INNER JOIN subject_table sub
    ON s.sessionSubjectId = sub.subjectId
    WHERE sub.subjectUserOwnerId = :userId
""")
     suspend fun getUserHistorySession(userId: String): List<SessionModel>




     @Query("""
    SELECT DISTINCT subjectId, subjectName
    FROM subject_table
    WHERE subjectUserOwnerId = :userId
""")
     suspend fun getAllUserUniqueSubjects(
          userId: String
     ): List<SubjectIdNameModel>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addNewSession(session: SessionModel)


}