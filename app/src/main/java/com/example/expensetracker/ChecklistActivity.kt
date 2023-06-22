package com.example.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.room.Room

class ChecklistActivity : AppCompatActivity() {
    private var listOfChecklists = ArrayList<Checklist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklists)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "listingdatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()

        val checklistDao = db.checklistDao()

        listOfChecklists = checklistDao.getAll() as ArrayList<Checklist>
        val names: ArrayList<String?> = ArrayList(listOfChecklists.map { it.name })

        var listing_list = findViewById<ListView>(R.id.listing_list)
        var adapter = ListingAdapter(this, names)
        listing_list.adapter = adapter

    }

    fun toListCreation(view: View) {}
}
