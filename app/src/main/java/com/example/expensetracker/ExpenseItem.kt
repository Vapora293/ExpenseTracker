package com.example.expensetracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Checklist::class,
        parentColumns = ["id"],
        childColumns = ["checklistId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExpenseItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "checklistId") val checklistId: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "balance") val balance: Double,
    @ColumnInfo(name = "isCrossedOut") var isCrossedOut: Boolean = false
)

