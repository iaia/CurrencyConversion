package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Live

@Dao
interface LiveDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: Live)

    // @Query("SELECT * FROM Live WHERE success = 1 AND source = :currency AND timestamp > :now ORDER BY timestamp DESC LIMIT 1")
    @Query("SELECT * FROM Live WHERE success = 1 AND source = :currency ORDER BY timestamp DESC LIMIT 1")
    fun getRecent(currency: String): Live?

    @Query("SELECT * FROM Live")
    fun getAll(): List<Live>
}