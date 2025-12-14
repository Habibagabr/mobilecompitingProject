package com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface

import com.habiba.studysmart.domain.model.SessionDomainModel

interface ISaveNewSessionUsecase {

    suspend operator fun invoke(session: SessionDomainModel)
}