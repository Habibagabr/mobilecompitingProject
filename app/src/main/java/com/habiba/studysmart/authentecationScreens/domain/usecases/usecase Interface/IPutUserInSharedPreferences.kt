package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase


interface IPutUserInSharedPreferences {
    suspend operator fun invoke(uid: String)
}
