package com.example.expensetracker

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.room.Room

class ExpenseItemActivity : AppCompatActivity() {
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

        val checklistId = intent.getStringExtra("listingId")
        if (checklistId != null) {
            listOfExpenses = expenseItemDao.getItemsByChecklist(checklistId) as ArrayList<ExpenseItem>
        }
        var expenseItemList = findViewById<ListView>(R.id.expense_list)
        var adapter = ExpenseItemAdapter(this, listOfExpenses)
        expenseItemList.adapter = adapter

        val createExpenseButton = findViewById<Button>(R.id.button_create_expense)
        createExpenseButton.setOnClickListener {
            if (checklistId != null) {
                toExpenseCreation(adapter, expenseItemDao, listOfExpenses, checklistId)
            }
        }
    }

    fun toExpenseCreation(
        adapter: ExpenseItemAdapter,
        expenseItemDao: ExpenseItemDao,
        expenseItems: ArrayList<ExpenseItem>,
        checklistId: String
    ) {
        val dialog = Dialog(this)
        dialog.setTitle("Create new Expense")
        dialog.setContentView(R.layout.expense_item_input_box)
        val createButton = dialog.findViewById<Button>(R.id.create_expense_button)
        createButton.setOnClickListener {
            val expenseNameInput = dialog.findViewById<EditText>(R.id.expense_name_input)
            val expenseCategoryInput = dialog.findViewById<EditText>(R.id.expense_category_input)
            val expenseBalanceInput = dialog.findViewById<EditText>(R.id.expense_balance_input)
            val newExpense = ExpenseItem(id = 0, checklistId = checklistId.toInt(), name = expenseNameInput.text.toString(), category = expenseCategoryInput.text.toString(), balance = expenseBalanceInput.text.toString().toDouble())
            expenseItemDao.insertAll(newExpense)
            expenseItems.add(newExpense)
            adapter.notifyDataSetChanged()

            dialog.dismiss()
        }
        dialog.show()
    }
}