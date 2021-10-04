package com.example.getrequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback

class CharacterAdapter(private val context: Callback<List<Data>>,
                       private val dataset: List<Data>) :
    RecyclerView.Adapter<CharacterAdapter.charcterViewHolder>() {

    class charcterViewHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textView: TextView = view.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): charcterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.character, parent, false)

        return charcterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: charcterViewHolder, position: Int) {
        val item = dataset[position]
        var str = ""

        str = str + item.name + "\n"+
                item.appearance + "\n" +
                item.better_call_saul_appearance + "\n" +
                item.img + "\n" +
                item.status + "\n"

        holder.textView.text = str
    }

    override fun getItemCount() = dataset.size


}