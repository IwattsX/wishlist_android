package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var WLItems: MutableList<WLItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize the WLItems list
        WLItems = mutableListOf(
            //WLItem("Item 1", "$10", "http://example.com/1"),
            //WLItem("Item 2", "$20", "http://example.com/2")
            // Add more items as needed
        )

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemsRv = findViewById<RecyclerView>(R.id.wishlist_items)

        // Create and set the adapter with the initialized WLItems list
        val adapter = WishListAdapter(WLItems)
        itemsRv.adapter = adapter
        itemsRv.layoutManager = LinearLayoutManager(this)


        val summitBtn = findViewById<Button>(R.id.button)


        val itemName = findViewById<EditText>(R.id.obTitle)
        val itemCost = findViewById<EditText>(R.id.cost)
        val itemURL = findViewById<EditText>(R.id.url)



        summitBtn.setOnClickListener{
            val itemsNameStr = itemName.text.toString()
            val itemCostStr = itemCost.text.toString()
            val itemURIStr = itemURL.text.toString()

            if(itemsNameStr == "" || itemCostStr == "" || itemURIStr == ""){
                Toast.makeText(it.context, "You need to enter in all fields", Toast.LENGTH_SHORT).show()

            }
            else {
                Toast.makeText(it.context, "You entered everything", Toast.LENGTH_SHORT).show()
                val newItem = WLItem(itemsNameStr, itemCostStr, itemURIStr)

                WLItems.add(newItem)
                adapter.notifyItemInserted(adapter.itemCount - 1)
            }

        }
    }
}
