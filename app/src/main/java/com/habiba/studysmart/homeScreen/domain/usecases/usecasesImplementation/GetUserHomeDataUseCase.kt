package com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation

import android.util.Log
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel
import com.habiba.studysmart.domain.repository.IHomeRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IGetUserHomeData
import javax.inject.Inject

class GetUserHomeDataUseCase@Inject constructor(
    private val homeRepository: IHomeRepository
): IGetUserHomeData {
    override suspend fun invoke(userId: String?): UserHomeDataDomainModel {
        Log.d("id","$userId")
        val data = homeRepository.getUserData(userId?:"1")
        Log.d("data","$data")
        return data


    }

}