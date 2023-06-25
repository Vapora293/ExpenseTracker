package com.example.expensetracker

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Checklist::class, ExpenseItem::class), version = 7)
abstract class AppDatabase : RoomDatabase() {
    abstract fun checklistDao(): ChecklistDao
    abstract fun expenseItemDao(): ExpenseItemDao
}