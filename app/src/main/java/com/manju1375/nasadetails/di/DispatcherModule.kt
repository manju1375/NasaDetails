package com.manju1375.nasadetails.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class DispatcherModule {

    companion object {
        @Provides
        @Named("DetailsDispatcher")
        fun provideDetailsDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }

}