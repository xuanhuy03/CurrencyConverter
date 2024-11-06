package com.huyngo.projecthuyandroid.myconverter.di

import com.huyngo.projecthuyandroid.myconverter.data.error.mapper.ErrorMapper
import com.huyngo.projecthuyandroid.myconverter.data.error.mapper.ErrorMapperSource
import com.huyngo.projecthuyandroid.myconverter.errors.ErrorManager
import com.huyngo.projecthuyandroid.myconverter.errors.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}