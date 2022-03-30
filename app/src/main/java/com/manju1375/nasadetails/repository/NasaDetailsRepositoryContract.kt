package com.manju1375.nasadetails.repository

import android.content.Context
import com.manju1375.nasadetails.model.NasaItemResponse

interface NasaDetailsRepositoryContract {
    fun getNasaDetails(context: Context): List<NasaItemResponse>
}