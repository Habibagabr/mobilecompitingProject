package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.domain.model.UserDomainModel

interface ICreateNewUser {
    suspend operator fun invoke(newUser: UserModel)
}