package com.site7x24learn.internshipfrontend.di

import com.site7x24learn.internshipfrontend.domain.repositories.AuthRepository
import com.site7x24learn.internshipfrontend.domain.repositories.InternshipRepository

import com.site7x24learn.internshipfrontend.domain.usecases.auth.LoginUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.auth.SignUpUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.internships.CreateInternshipUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.internships.DeleteInternshipUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.internships.GetCategoriesUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.internships.GetInternshipByIdUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.internships.GetInternshipsUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.internships.UpdateInternshipUseCase
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

    fun provideGetCategoriesUseCase(repository: InternshipRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository)
    }

    @Provides

    fun provideCreateInternshipUseCase(repository: InternshipRepository): CreateInternshipUseCase {
        return CreateInternshipUseCase(repository)
    }

    @Provides

    fun provideGetInternshipsUseCase(repository: InternshipRepository): GetInternshipsUseCase {
        return GetInternshipsUseCase(repository)
    }

    @Provides

    fun provideGetInternshipByIdUseCase(repository: InternshipRepository): GetInternshipByIdUseCase {
        return GetInternshipByIdUseCase(repository)
    }

    @Provides

    fun provideUpdateInternshipUseCase(repository: InternshipRepository): UpdateInternshipUseCase {
        return UpdateInternshipUseCase(repository)
    }

    @Provides

    fun provideDeleteInternshipUseCase(repository: InternshipRepository): DeleteInternshipUseCase {
        return DeleteInternshipUseCase(repository)
    }



}