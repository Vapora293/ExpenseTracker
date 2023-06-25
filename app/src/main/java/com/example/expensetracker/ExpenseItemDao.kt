package com.example.expensetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExpenseItemDao {
    @Query("SELECT * FROM expenseitem")
    fun getAll(): List<ExpenseItem>

    @Query("SELECT * FROM expenseitem WHERE checklistId=:checklistId")
    fun getItemsByChecklist(checklistId: String): List<ExpenseItem>

    @Query("SELECT SUM(balance) FROM expenseitem where balance > 0 AND isCrossedOut=0")
    fun getNegativeBalance(): Double

    @Query("SELECT SUM(balance) FROM expenseitem where balance < 0 AND isCrossedOut=0")
    fun getPositiveBalance(): Double

    @Query("SELECT SUM(balance) FROM expenseitem where isCrossedOut=0")
    fun getOverallBalance(): Double

    @Query("SELECT SUM(balance) from expenseitem WHERE checklistId=:checklistId AND isCrossedOut=0")
    fun getBalanceSumByChecklist(checklistId: String): Double

    @Insert
    fun insertAll(vararg expenseItems: ExpenseItem)

    @Delete
    fun delete(expenseItem: ExpenseItem)
    @Update
    fun update(expenseItem: ExpenseItem)
}
