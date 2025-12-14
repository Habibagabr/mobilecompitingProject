package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.domain.model.UserDomainModel
import com.habiba.studysmart.domain.repository.IHomeRepository
import javax.inject.Inject


class CreateNewUser@Inject constructor(
    private val homeRepository: IHomeRepository
): ICreateNewUser {
    override suspend fun invoke(newUser: UserModel) {
        homeRepository.addNewUser(newUser)
    }

}