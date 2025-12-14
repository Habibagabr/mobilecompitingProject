package com.habiba.studysmart.domain.usecases

import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.domain.usecases.usecase.ICheckUserExistenceUseCase
import javax.inject.Inject

class CheckUserExistenceUseCase@Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
): ICheckUserExistenceUseCase{

    override fun invoke(): Boolean {
        return authenticationRepository.checkUserExistence()
    }

}