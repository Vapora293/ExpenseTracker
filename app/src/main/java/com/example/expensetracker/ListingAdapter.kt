package com.example.expensetracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.room.Room

class ListingAdapter(
    private val context: Context,
    private val listings: ArrayList<String?>
) : ArrayAdapter<String?>(context, 0, listings) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listing_list_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val listingName = listings[position]
        holder.listName.text = listingName

        // Set click listener for the item view
        view?.setOnClickListener {
            val listingDao = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "listingdatabase"
            ).allowMainThreadQueries().enableMultiInstanceInvalidation()
                .fallbackToDestructiveMigration().build().listDao()
            moveToListItems(position, listingDao)
        }

        return view!!
    }

    private fun moveToListItems(position: Int, listingDao: DaoList) {
        val selectedListing = listings[position]

        val intent = Intent(context, ListItemsActivity::class.java)
        intent.putExtra("listingId", selectedListing?.let { listingDao.getIdByName(it) })
        context.startActivity(intent)
    }

    private class ViewHolder(view: View) {
        val listName: TextView = view.findViewById(R.id.list_name)
    }
}
