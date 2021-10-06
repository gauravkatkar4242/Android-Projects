package com.example.getrequest

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun intlistToJson(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonTointList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()

    @TypeConverter
    fun strlistToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonTostrList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()


}