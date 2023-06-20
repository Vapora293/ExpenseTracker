package com.example.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "listingdatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()
        val listDAO = db.listDao()
        val listing = Listing(id = 0, name = "Roza")
        listDAO.insertAll(listing)
    }

    fun toLists(view: View) {
        startActivity(Intent(this, ListActivity::class.java))
    }
    fun toStats(view: View) {
        startActivity(Intent(this, StatsActivity::class.java))
    }
}