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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
@Module
@InstallIn(FragmentComponent::class,ActivityComponent::class)
class NasaDetailsViewModel @Inject  constructor(@ApplicationContext var appContext: Context, @Named("DetailsDispatcher") val dispatcher: CoroutineDispatcher) : ViewModel() {
    var nasaDetails = MutableLiveData<List<NasaItemResponse>>()
    var isNetworkLost = MutableLiveData<Boolean>()
    var selectedItem = MutableLiveData<Int>()

    fun readDetails() {
        CoroutineScope(dispatcher).launch {
            val result = NasaDetailsRepository.getNasaDetails(appContext)
            nasaDetails.postValue(result)
        }
    }

    fun changeNetworkStatus(networkStatus: Boolean){
        isNetworkLost.postValue(networkStatus)
    }
}



