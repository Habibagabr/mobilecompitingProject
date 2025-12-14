package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import com.habiba.studysmart.authentecationScreens.domain.usecases.utils.LoginResult

interface ILoginUseCase {
    suspend operator fun invoke(email:String , password :String):LoginResult
}