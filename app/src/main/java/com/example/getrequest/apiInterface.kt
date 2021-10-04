package com.example.getrequest

import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {

    @GET("characters")
    fun getdata() : Call<List<Data>>
}
