package com.github.johnnysc.numberstesttask.numbers.data.cache

import android.content.Context
import androidx.room.Room

/**
 * @author Asatryan on 28.09.2022
 */
interface CacheModule {
    fun provideDataBase(): NumbersDataBase

    class Base(private val context: Context) : CacheModule {

        private val database by lazy {
            return@lazy Room.databaseBuilder(
                context,
                NumbersDataBase::class.java,
                "numbers_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        override fun provideDataBase(): NumbersDataBase = database
    }

    class Mock(private val context: Context) : CacheModule {
        private val database by lazy {
            Room.inMemoryDatabaseBuilder(context, NumbersDataBase::class.java)
                .build()
        }

        override fun provideDataBase(): NumbersDataBase = database
    }
}