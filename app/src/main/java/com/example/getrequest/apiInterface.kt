package com.example.getrequest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface apiInterface {

    @GET("characters")
    fun getdata(@Query("limit") limit: Int,
                @Query("offset") offset: Int): Call<List<Data>>
}
