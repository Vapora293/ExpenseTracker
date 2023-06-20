package com.example.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.room.Room

class ListActivity : AppCompatActivity() {
    private var listOfListings = ArrayList<Listing>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "listingdatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()

        val listingDao = db.listDao()

        listOfListings = listingDao.getAll() as ArrayList<Listing>
        val names: ArrayList<String?> = ArrayList(listOfListings.map { it.name })

        var listing_list = findViewById<ListView>(R.id.listing_list)
        var adapter = ListingAdapter(this, names)
        listing_list.adapter = adapter


    }

    fun toListCreation(view: View) {}
}