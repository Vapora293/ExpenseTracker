package com.example.expensetracker

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Listing::class, ListItem::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listDao(): DaoList
    abstract fun listItemDao(): DaoListItem
}