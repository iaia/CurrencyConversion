package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Rate

@Dao
interface RateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Rate>)

    @Query("SELECT * FROM Rate")
    fun getAll(): List<Rate>
}
