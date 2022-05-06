package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

// Adapts data from list and places it in the recyclerview
class ContactAdapter(private val context : Context, private val data : List<Contact>) :

    RecyclerView.Adapter<ContactAdapter.Viewholder>(){

    // Creates new views and inflates the item.xml to the new view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.Viewholder {
        return Viewholder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    //Binds the data at the current position to the View
    override fun onBindViewHolder(holder: ContactAdapter.Viewholder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val ivContact = itemView.findViewById<ImageView>(R.id.ivContact)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvAge = itemView.findViewById<TextView>(R.id.tvAge)
        fun bind(item : Contact){
            Picasso.get().load(item.imageUrl).into(ivContact)
            tvName.text = item.name
            tvAge.text = "I am ${item.age} yo"
        }
    }
}