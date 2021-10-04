package com.example.getrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMyData();
    }

    fun getMyData() {
        val retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.breakingbadapi.com/api/")
            .build()
            .create(apiInterface::class.java)

        val retrofitdata = retrofitbuilder.getdata()


        retrofitdata.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                val responsebody = response.body()!!
                var str = ""
                for (i in responsebody)
                {
                    str = str + i.name + "\n"
                }

                var data_textview = findViewById<Button>(R.id.data)
                data_textview.setText(str)
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                var data_textview = findViewById<Button>(R.id.data)
                data_textview.setText(t.message)
            }

        })

    }
}