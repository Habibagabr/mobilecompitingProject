package com.habiba.studysmart.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.habiba.studysmart.common.strings.APP_SHARED_PREFERENCE
import com.habiba.studysmart.data.local.dataSource.AppPreferences
import com.habiba.studysmart.data.local.dataSource.IAppPreference
import com.habiba.studysmart.data.repository.AuthenticationRepository
import com.habiba.studysmart.authentecationScreens.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ISignupUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.SignupUseCase
import com.habiba.studysmart.data.remote.dataSource.FirebaseAuthentication
import com.habiba.studysmart.data.remote.dataSource.IFirebaseAuthentication
import com.habiba.studysmart.domain.usecases.CheckUserExistenceUseCase
import com.habiba.studysmart.domain.usecases.usecase.ICheckUserExistenceUseCase
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
    fun provideFirebaseInstance () : FirebaseAuth {
        return Firebase.auth
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

}

