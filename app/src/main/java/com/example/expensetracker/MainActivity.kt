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
        val listDAO = db.checklistDao()
        val checklist = Checklist(id = 0, name = "Roza")
        listDAO.insertAll(checklist)
    }

    fun toLists(view: View) {
        startActivity(Intent(this, ChecklistActivity::class.java))
    }
    fun toStats(view: View) {
        startActivity(Intent(this, StatsActivity::class.java))
    }
}