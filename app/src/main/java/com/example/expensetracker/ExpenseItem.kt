package com.example.expensetracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "checklistId") val checklistid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "balance") val balance: Double
)
