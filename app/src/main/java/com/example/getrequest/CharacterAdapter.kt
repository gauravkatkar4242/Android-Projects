package com.example.getrequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CharacterAdapter : ListAdapter<Data, CharacterAdapter.CharacterViewHolder>(Diffutil()){

    class CharacterViewHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textView: TextView = view.findViewById(R.id.body)
        val imageView: ImageView = view.findViewById((R.id.image))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.character, parent, false)

        return CharacterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        var str = ""
        val url = item.img
        val picasso = Picasso.get()

        picasso.load(url)
            .into(holder.imageView)

        str = str + "Id: "+item.char_id + "\n" +
                "Name: " +item.name + "\n"+
                "Status: "+item.status + "\n" +
                "Positon: "+position + "\n"

//        if (position == Data)
        holder.textView.text = str

    }
    class Diffutil : DiffUtil.ItemCallback<Data>()
    {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.char_id == newItem.char_id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return  oldItem == newItem
        }

    }

}
