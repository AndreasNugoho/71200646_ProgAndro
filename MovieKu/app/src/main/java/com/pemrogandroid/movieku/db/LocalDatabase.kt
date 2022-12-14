package com.pemrogandroid.movieku.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.repository.IntegerListTypeConverter

@Database(entities = [Movie::class], version = 1)
@TypeConverters(IntegerListTypeConverter::class)
abstract class LocalDatabase : RoomDatabase() {

  // --- DAO ---
  abstract fun movieDao(): MovieDao

  companion object {

    // --- SINGLETON ---
    @Volatile
    private var INSTANCE: LocalDatabase? = null

    fun getInstance(context: Context): LocalDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          LocalDatabase::class.java,
          "Word_database"
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }
}
