package com.manju1375.nasadetails

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.manju1375.nasadetails.ui.viewmodel.NasaDetailsViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NasaDetailsViewModelTest {

    @Test
    fun testReadDetails(){
        val application = ApplicationProvider.getApplicationContext() as Context
        val testViewModel = NasaDetailsViewModel(application)
        testViewModel.readDetails()
    }
}