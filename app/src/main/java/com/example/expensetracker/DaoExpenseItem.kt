package com.example.expensetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoExpenseItem {
    @Query("SELECT * FROM expenseitem")
    fun getAll(): List<ExpenseItem>

    @Query("SELECT * FROM expenseitem WHERE checklistId=:checklistId")
    fun getItemsByChecklist(checklistId: String): List<ExpenseItem>

    @Insert
    fun insertAll(vararg expenseItems: ExpenseItem)

    @Delete
    fun delete(expenseItem: ExpenseItem)
}
