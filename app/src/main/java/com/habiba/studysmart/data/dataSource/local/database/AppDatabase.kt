package com.habiba.studysmart.data.dataSource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.data.model.SubjectModel
import com.habiba.studysmart.data.model.TaskModel
import com.habiba.studysmart.data.model.UserModel

@Database(
    entities = [
        UserModel::class,
        SubjectModel::class,
        TaskModel::class,
        SessionModel::class
    ],
    version = 100,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}
