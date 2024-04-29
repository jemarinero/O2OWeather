package com.o2o.domain.di

import com.o2o.domain.dispatcher.DefaultDispatcherProvider
import com.o2o.domain.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindDispatcherProvider(imp: DefaultDispatcherProvider): DispatcherProvider
}