package com.manju1375.nasadetails.adapter

import com.manju1375.nasadetails.model.NasaItemResponse
import com.manju1375.nasadetails.ui.adapter.NasaImageListAdapter
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.lang.reflect.Field

class NasaImageAdapterTest {
    var dataList = mutableListOf<NasaItemResponse>()
    private lateinit var nasaImageListAdapter: NasaImageListAdapter

    @Before
    fun setUp(){
        nasaImageListAdapter = Mockito.mock(NasaImageListAdapter::class.java)
        dataList.add(NasaItemResponse(null,null))
        val myVarField: Field = NasaImageListAdapter::class.java.getDeclaredField("dataList")
        myVarField.setAccessible(true)
        myVarField.set(nasaImageListAdapter, dataList)
    }
    @Test
    fun testSetList(){
       nasaImageListAdapter.setDetails(dataList)
       TestCase.assertTrue(dataList.size==1)
    }

}