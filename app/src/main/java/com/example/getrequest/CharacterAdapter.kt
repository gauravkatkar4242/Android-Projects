package com.example.getrequest

import android.graphics.BitmapFactory
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Callback

class CharacterAdapter(private val context: Callback<List<Data>>,
                       private val dataset: List<Data>) :
    RecyclerView.Adapter<CharacterAdapter.charcterViewHolder>() {

    class charcterViewHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textView: TextView = view.findViewById(R.id.body)
        val imageView: ImageView = view.findViewById((R.id.image))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): charcterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.character, parent, false)

        return charcterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: charcterViewHolder, position: Int) {
        val item = dataset[position]
        var str = ""
        val url = item.img
        val picasso = Picasso.get()

        picasso.load(url)
            .into(holder.imageView)

        str = str + "Name: " +item.name + "\n"+
               "Status: "+item.status + "\n"

        holder.textView.text = str
    }

    override fun getItemCount() = dataset.size


}