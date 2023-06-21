package com.example.expensetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoListItem {
    @Query("SELECT * FROM listing")
    fun getAll(): List<Listing>

    @Query("SELECT id FROM listing WHERE name=:name")
    fun getIdByName(name: String): Int

    @Insert
    fun insertAll(vararg listings: Listing)

    @Delete
    fun delete(listing: Listing)
}
