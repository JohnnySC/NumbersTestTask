package com.github.johnnysc.numberstesttask.numbers.data.cache

import androidx.room.*

/**
 * @author Asatryan on 28.09.2022
 */
@Dao
interface NumbersDao {

    @Query("SELECT * FROM numbers_table ORDER BY date ASC")
    fun allNumbers(): List<NumberCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(number: NumberCache)

    @Query("SELECT * FROM numbers_table WHERE number = :number")
    fun number(number: String): NumberCache?
}