package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class WishListAdapter(private val WLItems: MutableList<WLItem>) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.item)
        val costTextView: TextView = itemView.findViewById(R.id.cost_item)
        val urlTextView: TextView = itemView.findViewById(R.id.URL_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return WLItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val WLItem = WLItems[position]
        holder.itemTextView.text = WLItem.item
        holder.costTextView.text = WLItem.cost
        holder.urlTextView.text = WLItem.url

        // Handle item click to open URL
        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(WLItem.url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + WLItem.item, Toast.LENGTH_LONG).show()
            }
        }

        // Handle long press to delete an item
        holder.itemView.setOnLongClickListener {
            WLItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, WLItems.size)
            true
        }
    }
}
