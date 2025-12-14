package com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface

import com.habiba.studysmart.domain.model.UserHomeDataDomainModel

interface IGetUserHomeData{
    suspend operator fun invoke(userId: String? ="1"): UserHomeDataDomainModel
}