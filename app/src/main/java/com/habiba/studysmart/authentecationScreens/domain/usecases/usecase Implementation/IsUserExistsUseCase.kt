package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class IsUserExistsUseCase @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : IIsUserExistsUseCase {

    override suspend operator fun invoke(userId: String): Boolean {
        return authenticationRepository.isUserInDB(userId)
    }
}
