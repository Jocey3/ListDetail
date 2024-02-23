package dev.jocey.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jocey.data.model.local.NumberEntity

@Database(entities = [NumberEntity::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun numbersDao(): NumberDao
}