package com.example.getrequest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Charactres : Fragment() {

    lateinit var recyclerView: RecyclerView
    var limit = 0
    var offset = 0
    var responsebody: List<Data> = emptyList()
    val adapter = CharacterAdapter()
    lateinit var retrofitbuilder: apiInterface
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_charactres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        limit = 5
        offset = -5
        var add_data: Button = requireView().findViewById(R.id.add_data)

        recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view)


        retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.breakingbadapi.com/api/")
            .build()
            .create(apiInterface::class.java)
        offset = offset + limit
        var responsebody = getData(limit, offset)

        add_data.setOnClickListener {
            offset = offset + limit
            responsebody = getData(limit, offset)
            Log.d("CharacterFragment", responsebody.toString())
        }
    }


    fun getData(limit: Int, offset: Int)
    {
        val retrofitdata = retrofitbuilder.getdata(limit, offset)
        retrofitdata.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {

                Toast.makeText(requireContext(), "${response.body()?.count()} records Loaded Successfully", Toast.LENGTH_SHORT).show()
                responsebody = responsebody + response.body()!!
                addDataToRecyclerView(responsebody)
            }
            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                var data_textview = requireView().findViewById<TextView>(R.id.error_textview)
                data_textview.setText("Error!!! \n"+ t.message)
            }
        })
    }

    fun addDataToRecyclerView(responsebody: List<Data>){

        adapter.submitList(responsebody){
            recyclerView.scrollToPosition(responsebody.size - limit)
        }
        recyclerView.setHasFixedSize( true)
        recyclerView.adapter = adapter

    }
}