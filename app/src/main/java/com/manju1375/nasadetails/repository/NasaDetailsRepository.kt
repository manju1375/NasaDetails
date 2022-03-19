package com.manju1375.nasadetails.repository

import android.content.Context
import com.manju1375.nasadetails.R
import com.manju1375.nasadetails.model.NasaItemResponse
import com.manju1375.nasadetails.utils.jsonToClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object NasaDetailsRepository {

    @Singleton
    @Provides
    fun getNasaDetails(context: Context): List<NasaItemResponse> {
        return context.jsonToClass(R.raw.data)
    }
}