package com.site7x24learn.internshipfrontend.di

import com.site7x24learn.internshipfrontend.data.datasources.local.PreferencesManager
import com.site7x24learn.internshipfrontend.data.datasources.remote.ApiService
import com.site7x24learn.internshipfrontend.data.repositories.AuthRepositoryImpl
import com.site7x24learn.internshipfrontend.data.repositories.InternshipRepositoryImpl
import com.site7x24learn.internshipfrontend.data.repositories.StudentStatusRepositoryImpl

import com.site7x24learn.internshipfrontend.domain.repositories.AuthRepository
import com.site7x24learn.internshipfrontend.domain.repositories.InternshipRepository
import com.site7x24learn.internshipfrontend.domain.repositories.StudentStatusRepository
import com.site7x24learn.internshipfrontend.domain.usecases.student.GetStudentsUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.student.UpdateStudentStatusUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        preferencesManager: PreferencesManager
    ): AuthRepository {
        return AuthRepositoryImpl(apiService, preferencesManager)
    }

    @Provides
    @Singleton
    fun provideInternshipRepository(
        apiService: ApiService
    ): InternshipRepository {
        return InternshipRepositoryImpl(apiService)
    }
    @Provides
    fun provideStudentStatusRepository(api: ApiService): StudentStatusRepository {
        return StudentStatusRepositoryImpl(api)
    }
    @Provides
    fun provideGetStudentsUseCase(repository: StudentStatusRepository): GetStudentsUseCase {
        return GetStudentsUseCase(repository)
    }


}