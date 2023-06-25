package com.example.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var expenseItemDao: ExpenseItemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the database and DAO
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "listingdatabase"
        ).allowMainThreadQueries()
            .enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration()
            .build()

        expenseItemDao = db.expenseItemDao()

        // Display Dao methods in TextViews
        displayNegativeBalance()
        displayPositiveBalance()
        displayOverallBalance()
    }

    override fun onResume() {
        super.onResume()

        displayNegativeBalance()
        displayPositiveBalance()
        displayOverallBalance()
    }

    fun toLists(view: View) {
        startActivity(Intent(this, ChecklistActivity::class.java))
    }

    private fun displayNegativeBalance() {
        val textViewNegativeBalance = findViewById<TextView>(R.id.textViewOverallExpenses)
        val negativeBalance = expenseItemDao.getNegativeBalance()
        textViewNegativeBalance.text = "Negative Balance: -$negativeBalance"
    }

    private fun displayPositiveBalance() {
        val textViewPositiveBalance = findViewById<TextView>(R.id.textViewOverallIncome)
        val positiveBalance = expenseItemDao.getPositiveBalance() * -1
        textViewPositiveBalance.text = "Positive Balance: $positiveBalance"
    }

    private fun displayOverallBalance() {
        val textViewOverallBalance = findViewById<TextView>(R.id.textViewOverallBalance)
        val overallBalance = expenseItemDao.getOverallBalance() * -1
        textViewOverallBalance.text = "Overall Balance: $overallBalance"
    }
}