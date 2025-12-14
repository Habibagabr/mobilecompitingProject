package com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation

import com.habiba.studysmart.data.repository.HomeRepository
import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.ILogoutUsecase
import javax.inject.Inject

class LogoutUsecase@Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
): ILogoutUsecase {
    override fun invoke() {
        authenticationRepository.logout()
    }
}