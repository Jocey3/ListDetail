package dev.jocey.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers")
data class NumberEntity(
    @PrimaryKey
    val number: Int,
    val text: String,
    val found: Boolean,
    val type: String?
)
