package com.habiba.studysmart.data.dataSource.local.sharedPreference

interface IAppPreference {
    fun getUserId():String?
    fun putUserId(userId: String?)
    fun clearUserSession()
}