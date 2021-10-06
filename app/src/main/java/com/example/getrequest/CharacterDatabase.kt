package com.example.getrequest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Data::class],version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao() : CharacterDAO

    companion object{
        @Volatile
        private var INSTANCE : CharacterDatabase? = null

        fun getDatabase(context : Context): CharacterDatabase
        {
            if (INSTANCE == null)
            {
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CharacterDatabase::class.java,
                        "CharacterDB"
                    ).build()
                }

            }
            return INSTANCE!!
        }

    }

}