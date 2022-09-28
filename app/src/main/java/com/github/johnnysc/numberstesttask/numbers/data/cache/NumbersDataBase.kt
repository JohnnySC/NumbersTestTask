package com.github.johnnysc.numberstesttask.numbers.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Asatryan on 28.09.2022
 */
@Database(entities = [NumberCache::class], version = 1)
abstract class NumbersDataBase : RoomDatabase() {

    abstract fun numbersDao() : NumbersDao
}