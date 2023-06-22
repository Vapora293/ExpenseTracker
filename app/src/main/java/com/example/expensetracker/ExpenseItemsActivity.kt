package com.example.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.room.Room

class ExpenseItemsActivity : AppCompatActivity() {
    private var listOfExpenses = ArrayList<ExpenseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_items)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "listingdatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()

        val expenseItemDao = db.expenseItemDao()

        val expense = ExpenseItem(id = 0, checklistid = 1, name = "Testing Expense", category = "Hobby", balance = 14.85)
        expenseItemDao.insertAll(expense)

        val listingId = intent.getStringExtra("listingId")
        if (listingId != null) {
            Log.i("ID", listingId)
        }
        else {
            Log.i("NULL", "null")
        }
        if (listingId != null) {
            listOfExpenses = expenseItemDao.getItemsByChecklist(listingId) as ArrayList<ExpenseItem>
            Log.i("Yes", listOfExpenses.toString())
        }
        var expenseItemList = findViewById<ListView>(R.id.expense_list)
        var adapter = ExpenseItemAdapter(this, listOfExpenses)
        expenseItemList.adapter = adapter
    }

    fun toExpenseCreation(view: View) {}
}