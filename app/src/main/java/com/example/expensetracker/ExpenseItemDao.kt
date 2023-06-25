package com.example.expensetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseItemDao {
    @Query("SELECT * FROM expenseitem")
    fun getAll(): List<ExpenseItem>

    @Query("SELECT * FROM expenseitem WHERE checklistId=:checklistId")
    fun getItemsByChecklist(checklistId: String): List<ExpenseItem>

    @Query("SELECT SUM(balance) from expenseitem WHERE checklistId=:checklistId")
    fun getBalanceSumByChecklist(checklistId: String): Double

    @Insert
    fun insertAll(vararg expenseItems: ExpenseItem)

    @Delete
    fun delete(expenseItem: ExpenseItem)
}
