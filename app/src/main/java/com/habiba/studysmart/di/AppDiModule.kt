package com.habiba.studysmart.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.CreateNewUser
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ICreateNewUser
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.IIsUserExistsUseCase
import com.habiba.studysmart.common.strings.APP_SHARED_PREFERENCE
import com.habiba.studysmart.data.dataSource.local.sharedPreference.AppPreferences
import com.habiba.studysmart.data.dataSource.local.sharedPreference.IAppPreference
import com.habiba.studysmart.data.repository.AuthenticationRepository
import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ILoginUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.IPutUserInSharedPreferences
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ISignupUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.IsUserExistsUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.LoginUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.PutUserInSharedPreferences
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.SignupUseCase
import com.habiba.studysmart.data.dataSource.local.database.AppDao
import com.habiba.studysmart.data.dataSource.local.database.AppDatabase
import com.habiba.studysmart.data.dataSource.local.database.DatabaseServices
import com.habiba.studysmart.data.dataSource.local.database.IDatabaseServices
import com.habiba.studysmart.data.dataSource.remote.firebase.FirebaseAuthentication
import com.habiba.studysmart.data.dataSource.remote.firebase.IFirebaseAuthentication
import com.habiba.studysmart.data.repository.HomeRepository
import com.habiba.studysmart.data.repository.SessionRepository
import com.habiba.studysmart.data.repository.SubjectRepository
import com.habiba.studysmart.domain.repository.IHomeRepository
import com.habiba.studysmart.domain.repository.ISessionRepository
import com.habiba.studysmart.domain.repository.ISubjectRepository
import com.habiba.studysmart.domain.usecases.CheckUserExistenceUseCase
import com.habiba.studysmart.domain.usecases.usecase.ICheckUserExistenceUseCase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.AddNewSubjectUseCaseAndUpdateUser
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.GetUserHomeDataUseCase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.GetUserIdUseCase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.LogoutUsecase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.MarkTaskAsCompletedUsecase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IAddNewSubjectUseCaseAndUpdateUser
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IGetUserHomeData
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IGetUserIdUsecase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.ILogoutUsecase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IMarkTaskAsCompletedUseCase
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesImplementation.GetUserHistorySessionsUsecase
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesImplementation.GetUserUniqueSubjectsUsecase
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesImplementation.SaveNewSessionUsecase
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.IGetUserHistorySessionsUsecase
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.IGetUserUniqueSubjectsUsecase
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.ISaveNewSessionUsecase
import com.habiba.studysmart.subjectScreen.domain.usecasesImplementation.AddNewTaskUseCase
import com.habiba.studysmart.subjectScreen.domain.usecasesImplementation.GetSubjectDetailsUseCase
import com.habiba.studysmart.subjectScreen.domain.usecasesInterface.IAddNewTaskUseCase
import com.habiba.studysmart.subjectScreen.domain.usecasesInterface.IGetSubjectDetailsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// provide module to provide an INSTANCE of manually constructed objects
@Module
@InstallIn(SingletonComponent::class)
object AppProvidesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences(APP_SHARED_PREFERENCE, MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideFirebaseInstance(): FirebaseAuth {
        return Firebase.auth
    }


    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
                app,
                AppDatabase::class.java,
                "studysmart_database"
            ).fallbackToDestructiveMigration(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase): AppDao {
        return database.appDao()

    }
}




// bind module to bind constructed Implementation to an interface

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindModule {

    @Binds
    abstract fun bindSharedPreference(
        impl: AppPreferences
    ): IAppPreference

    @Binds
    abstract fun bindAuthRepository(
        impl: AuthenticationRepository
    ): IAuthenticationRepository

    @Binds
    abstract fun bindCheckUserExistenceUseCase(
        impl: CheckUserExistenceUseCase
    ): ICheckUserExistenceUseCase
    
    @Binds
    abstract fun bindSignupUseCase(
        impl: SignupUseCase
    ): ISignupUseCase

    @Binds
    abstract fun bindFirebaseAuthentication(
        impl: FirebaseAuthentication
    ): IFirebaseAuthentication


    @Binds
    abstract fun bindPutUserInSharedPreferencesUseCase(
        impl: PutUserInSharedPreferences
    ): IPutUserInSharedPreferences

    @Binds
    abstract fun bindLoginUseCase(
        impl: LoginUseCase
    ): ILoginUseCase

    @Binds
    abstract fun bindDataServices(
        imp: DatabaseServices
    ): IDatabaseServices

    @Binds
    abstract fun bindHomeRepository(
        imp: HomeRepository
    ): IHomeRepository

    @Binds
    abstract fun bindGetUserIdUseCase(
        imp: GetUserIdUseCase
    ): IGetUserIdUsecase

    @Binds
    abstract fun bindGetUserHomeData(
        imp: GetUserHomeDataUseCase
    ): IGetUserHomeData

    @Binds
    abstract fun bindCreateUserUseCase(
        imp: CreateNewUser
    ): ICreateNewUser

    @Binds
    abstract fun bindAddNewSubjectAndUpdateUseCase(
        imp: AddNewSubjectUseCaseAndUpdateUser
    ): IAddNewSubjectUseCaseAndUpdateUser

    @Binds
    abstract fun bindSubjectRepository(
        imp: SubjectRepository
    ): ISubjectRepository

    @Binds
    abstract fun bindGetSubjectDetailsUseCase(
        imp: GetSubjectDetailsUseCase
    ): IGetSubjectDetailsUseCase

    @Binds
    abstract fun bindAddNewTask(
        imp: AddNewTaskUseCase
    ): IAddNewTaskUseCase


    @Binds
    abstract fun bindSessionsRepository(
        imp: SessionRepository
    ): ISessionRepository

    @Binds
    abstract fun bindGetUserUniqueSubjectsUseCase(
        imp: GetUserUniqueSubjectsUsecase
    ): IGetUserUniqueSubjectsUsecase

    @Binds
    abstract fun bindGetUserSessionHistoryUseCase(
        imp: GetUserHistorySessionsUsecase
    ): IGetUserHistorySessionsUsecase

    @Binds
    abstract fun bindSaveNewSessionUsecase(
        imp: SaveNewSessionUsecase
    ): ISaveNewSessionUsecase

    @Binds
    abstract fun bindMarkTaskAsCompletedUseCase(
        imp: MarkTaskAsCompletedUsecase
    ): IMarkTaskAsCompletedUseCase

    @Binds
    abstract fun bindLogoutUsecase(
        imp: LogoutUsecase
    ):ILogoutUsecase

    @Binds
    abstract fun bindIsUserExist(
        imp: IsUserExistsUseCase
    ): IIsUserExistsUseCase








}

