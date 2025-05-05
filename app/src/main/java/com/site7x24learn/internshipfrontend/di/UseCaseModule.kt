package com.site7x24learn.internshipfrontend.di

import com.site7x24learn.internshipfrontend.domain.repositories.AuthRepository
import com.site7x24learn.internshipfrontend.domain.repositories.InternshipRepository

import com.site7x24learn.internshipfrontend.domain.usecases.auth.LoginUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.auth.SignUpUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.internships.CreateInternshipUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.internships.GetCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    // Auth Use Cases
    @Provides
    fun provideLoginUseCase(repo: AuthRepository) = LoginUseCase(repo)

    @Provides
    fun provideSignUpUseCase(repo: AuthRepository) = SignUpUseCase(repo)

    // Internship Use Cases
    @Provides
    fun provideGetCategoriesUseCase(repo: InternshipRepository) = GetCategoriesUseCase(repo)

    @Provides
    fun provideCreateInternshipUseCase(repo: InternshipRepository) = CreateInternshipUseCase(repo)



}