package org.mousehole.restolocatorkameljohn.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = arrayOf(LocationPlace::class))
abstract class LocationDatabase: RoomDatabase() {

    abstract fun locationDao(): LocationDao

    companion object{
        const val DATABASE_NAME: String = "location.db"

        private var INSTANCE: LocationDatabase? = null

        fun getDatabase(context: Context): LocationDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocationDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}