package com.example.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

private const val TAG = "Adapter"
private const val CONTACT= 0
private const val AD = 1

// Adapts data from list and places it in the recyclerview
class ContactAdapter(private val context : Context, private val data : List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return data.size
    }
    // Returns the viewType at the position
    override fun getItemViewType(position: Int): Int {
        return if (data[position] is Contact){
            CONTACT
        } else{
            AD
        }
    }
    //Inflates the respective xml for each item type
    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return when (viewType){
            CONTACT -> ContactViewholder(inflater.inflate(R.layout.item, parent, false))
            else -> {AdViewholder(inflater.inflate(R.layout.ad_item, parent, false))}
        }
    }
    //Binds the data at position to the view
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder.itemViewType){
            CONTACT -> {
                Log.i(TAG, "I am Contact at pos $position")
                val vh1 = viewHolder as ContactViewholder
                bindContact(vh1, position)
            }
            AD -> {
                val vh2 = viewHolder as AdViewholder
                bindAd(vh2, position)
            }
        }
    }

    inner class ContactViewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ivContact : ImageView = itemView.findViewById(R.id.ivContact)
        val tvName : TextView = itemView.findViewById(R.id.tvName)
        val tvAge : TextView = itemView.findViewById(R.id.tvAge)

    }

    inner class AdViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvAd: TextView = itemView.findViewById(R.id.tvAd)
    }

    @SuppressLint("SetTextI18n")
    private fun bindContact(vh : ContactViewholder, position: Int){
        val contact = data[position] as Contact
        Picasso.get().load(contact.imageUrl).into(vh.ivContact)
        vh.tvName.text = contact.name
        vh.tvAge.text = "I am ${contact.age} yo"
    }
    private fun bindAd(vh2: AdViewholder, position: Int) {
        val ad = data[position] as Advertisment
        vh2.tvAd.text = ad.message
    }
}
