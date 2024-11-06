package com.huyngo.projecthuyandroid.myconverter.di

import com.huyngo.projecthuyandroid.myconverter.data.DataRepository
import com.huyngo.projecthuyandroid.myconverter.data.DataRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepo(dataRepository: DataRepository): DataRepositorySource
}
