package com.manju1375.nasadetails

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manju1375.nasadetails.model.NasaItemResponse
import com.manju1375.nasadetails.repository.NasaDetailsRepository
import com.manju1375.nasadetails.ui.viewmodel.NasaDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.powermock.api.mockito.PowerMockito
import java.io.ByteArrayInputStream
import java.lang.reflect.Type


class NasaDetailsViewModelTest {
    private lateinit var viewModel: NasaDetailsViewModel
    private var dataList = mutableListOf<NasaItemResponse>()
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()
    private var type: Type = object : TypeToken<List<NasaItemResponse>?>() {}.type
    private var context: Context? = null
    private var repo: NasaDetailsRepository? = null
    private var gson:Gson? = null
    private lateinit var jsonString: String

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repo = PowerMockito.mock(NasaDetailsRepository::class.java)
        context = Mockito.mock(Context::class.java)
        val resources = Mockito.mock(Resources::class.java)
        gson = Mockito.mock(Gson::class.java)
        // test input stream
        val inputStream = ByteArrayInputStream(
            "test inputStream".toByteArray()
        )
        val bufferedReader = inputStream.bufferedReader()
        bufferedReader.use { it.readText() }.also { jsonString = it }
        viewModel = NasaDetailsViewModel(context!!, testDispatcher)
        dataList.add(NasaItemResponse(null, null))
        Mockito.`when`(context!!.resources).thenReturn(resources)
        PowerMockito.whenNew(Gson::class.java).withAnyArguments().thenReturn(gson)
        Mockito.`when`(gson?.fromJson(jsonString, type) as List<NasaItemResponse>?).thenReturn(dataList)
        Mockito.`when`(resources.openRawResource(any(Int::class.java))).thenReturn(inputStream)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testNasaDetails() {
        CoroutineScope(testDispatcher).launch {
            viewModel.readDetails()
            assertEquals(repo?.getNasaDetails(context!!), dataList)
        }
    }


}