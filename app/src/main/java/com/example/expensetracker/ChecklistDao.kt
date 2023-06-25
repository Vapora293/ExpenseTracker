package com.example.expensetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChecklistDao {
    @Query("SELECT * FROM checklist")
    fun getAll(): List<Checklist>

    @Query("SELECT id FROM checklist WHERE name=:name")
    fun getIdByName(name: String): Int

    @Insert
    fun insertAll(vararg checklists: Checklist)

    @Delete
    fun delete(checklist: Checklist)
}
