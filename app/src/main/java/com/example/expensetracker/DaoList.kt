package com.example.expensetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoList {
    @Query("SELECT * FROM listing")
    fun getAll(): List<Listing>

    @Insert
    fun insertAll(vararg users: Listing)

    @Delete
    fun delete(user: Listing)
}