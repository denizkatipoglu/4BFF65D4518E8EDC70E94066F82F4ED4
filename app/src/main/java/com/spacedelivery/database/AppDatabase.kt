package com.spacedelivery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Deniz KATIPOGLU on 03,May,2021
 */

@Database(
    entities = [SpaceName::class,PlanetDB::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun TodoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "todo-list.db"
        )
            .build()
    }
}