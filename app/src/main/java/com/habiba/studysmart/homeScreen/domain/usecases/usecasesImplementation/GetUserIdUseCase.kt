package com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation

import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IGetUserIdUsecase
import javax.inject.Inject

class GetUserIdUseCase@Inject constructor(
    private val authenticationRepo: IAuthenticationRepository
): IGetUserIdUsecase {
    override fun invoke():String? {
        return authenticationRepo.getUserId()?:"1"

    }

}