package com.example.myroomdbapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [CarEntity::class])
abstract class CarDatabase : RoomDatabase(){
    abstract fun carDAO(): CarDAO
}
