package com.github.johnnysc.numberstesttask.numbers.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Asatryan on 28.09.2022
 */
@Entity(tableName = "numbers_table")
data class NumberCache(
    @PrimaryKey @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "fact") val fact: String,
    @ColumnInfo(name = "date") val date: Long
)