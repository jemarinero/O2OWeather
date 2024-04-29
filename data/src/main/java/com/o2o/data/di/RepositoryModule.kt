package com.o2o.data.di

import com.o2o.data.repository.WeatherRepositoryImpl
import com.o2o.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [RepositoryModule.RepositorySubModule::class])
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface RepositorySubModule {

        @Binds
        fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
    }
}