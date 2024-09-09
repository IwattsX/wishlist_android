package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class WishListAdapter(private val WLItems: List<WLItem>) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Your holder should contain a member variable for any view that will be set as you render
        // a row
        val itemTextView: TextView
        val costTextView: TextView
        val urlTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            itemTextView = itemView.findViewById(R.id.item)
            costTextView = itemView.findViewById(R.id.cost_item)
            urlTextView = itemView.findViewById(R.id.URL_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return WLItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val WLItem = WLItems[position]
        // Set item views based on views and data model
        holder.itemTextView.text = WLItem.item
        holder.costTextView.text = WLItem.cost
        holder.urlTextView.text = WLItem.url
    }
}