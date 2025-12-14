package com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface

import com.habiba.studysmart.domain.model.SessionDomainModel

interface IGetUserHistorySessionsUsecase {
    suspend operator fun invoke(userId:String):List<SessionDomainModel>
}