package dev.jocey.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.jocey.data.model.local.NumberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {
    @Query("SELECT * FROM numbers ORDER BY id ASC")
    fun getAll(): Flow<List<NumberEntity>>

    @Query("SELECT * FROM numbers WHERE number LIKE :number")
    suspend fun findByNumber(number: Int): List<NumberEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(numbers: List<NumberEntity>)

    @Update
    suspend fun updateNumber(number: NumberEntity)

    @Delete
    suspend fun delete(number: NumberEntity)
}