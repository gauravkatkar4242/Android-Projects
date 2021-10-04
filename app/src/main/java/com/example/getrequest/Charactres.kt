package com.example.getrequest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Charactres : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_charactres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.breakingbadapi.com/api/")
            .build()
            .create(apiInterface::class.java)

        val retrofitdata = retrofitbuilder.getdata()


        retrofitdata.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                val responsebody = response.body()!!
//                var str = ""
//                for (i in responsebody)
//                {
//                    str = str + i.name + "\n"
//                }
//
//                var data_textview = findViewById<Button>(R.id.data)
//                data_textview.setText(str)
                Log.d("CharcterFragment", "onResponse Success")
                val recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view)
                recyclerView.adapter = CharacterAdapter(this, responsebody)

                // Use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                recyclerView.setHasFixedSize(true)

            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                var data_textview = requireView().findViewById<Button>(R.id.data)
                data_textview.setText(t.message)
            }

        })
    }
}