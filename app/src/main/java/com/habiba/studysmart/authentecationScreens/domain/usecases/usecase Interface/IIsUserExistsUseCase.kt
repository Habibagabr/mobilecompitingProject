package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase


interface IIsUserExistsUseCase {
    suspend operator fun invoke(userId: String): Boolean
}
