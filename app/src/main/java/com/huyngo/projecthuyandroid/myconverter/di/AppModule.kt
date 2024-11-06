package com.huyngo.projecthuyandroid.myconverter.di

import android.content.Context
import com.huyngo.projecthuyandroid.myconverter.utils.INetworkConnectivity
import com.huyngo.projecthuyandroid.myconverter.utils.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCoroutineContext() : CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext mContext: Context) : INetworkConnectivity {
        return Network(mContext)
    }

}