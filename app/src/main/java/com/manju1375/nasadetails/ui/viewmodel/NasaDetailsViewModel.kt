package com.manju1375.nasadetails.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manju1375.nasadetails.model.NasaItemResponse
import com.manju1375.nasadetails.repository.NasaDetailsRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@Module
@InstallIn(FragmentComponent::class,ActivityComponent::class)
class NasaDetailsViewModel @Inject constructor(@ApplicationContext var appContext: Context) : ViewModel() {
    val nasaDetails = MutableLiveData<List<NasaItemResponse>>()
    val progressBar = MutableLiveData<Boolean>()
    var isNetworkLost = MutableLiveData<Boolean>()
    var selectedItem = MutableLiveData<Int>()

    fun readDetails() {
        progressBar.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val result = NasaDetailsRepository.getNasaDetails(appContext)
            nasaDetails.postValue(result)
        }
        progressBar.value = false
    }

    fun changeNetworkStatus(networkStatus: Boolean){
        isNetworkLost.postValue(networkStatus)
    }
}



