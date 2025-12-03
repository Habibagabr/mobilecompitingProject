package com.habiba.studysmart.data.local.dataSource

interface IAppPreference {
    fun getUserId():String?
    fun putUserId(userId: String?)
    fun clearUserSession()
}