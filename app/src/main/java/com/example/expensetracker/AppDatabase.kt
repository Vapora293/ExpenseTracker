package com.example.expensetracker

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Checklist::class, ExpenseItem::class), version = 8)
abstract class AppDatabase : RoomDatabase() {
    abstract fun checklistDao(): ChecklistDao
    abstract fun expenseItemDao(): ExpenseItemDao
}