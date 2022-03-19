package com.manju1375.nasadetails.utils

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manju1375.nasadetails.model.NasaItemResponse
import java.lang.reflect.Type


var type: Type = object : TypeToken<List<NasaItemResponse>?>() {}.type

inline fun <reified T> Context.jsonToClass(@RawRes resourceId: Int): T =
    Gson().fromJson(resources.openRawResource(resourceId).bufferedReader().use { it.readText() }, type)