package com.manju1375.nasadetails.adapter

import com.manju1375.nasadetails.model.NasaItemResponse
import com.manju1375.nasadetails.ui.adapter.NasaDetailsPagerAdapter
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.lang.reflect.Field

class NasaDetailsPagerAdapterTest {
    var pageList = mutableListOf<NasaItemResponse>()
    private lateinit var nasaDetailsPagerAdapter: NasaDetailsPagerAdapter

    @Before
    fun setUp(){
        nasaDetailsPagerAdapter = Mockito.mock(NasaDetailsPagerAdapter::class.java)
        pageList.add(NasaItemResponse(null,null))
        val myVarField: Field = NasaDetailsPagerAdapter::class.java.getDeclaredField("pages")
        myVarField.setAccessible(true)
        myVarField.set(nasaDetailsPagerAdapter, pageList)
    }
    @Test
    fun testSetList(){
       nasaDetailsPagerAdapter.setNasaDetails(pageList)
       TestCase.assertTrue(pageList.size==1)
    }

}