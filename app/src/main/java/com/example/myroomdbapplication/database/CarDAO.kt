package com.example.myroomdbapplication.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDAO {

    @Insert
    fun addNewCar(carEntity: CarEntity)

    @Query("SELECT * FROM Cars")
    fun retrieveAllCars(): List<CarEntity>

//    @Query("SELECT carId FROM Cars where carName = :name")
//    fun retrieveCarId(name: String): CarEntity

    @Delete
    fun deleteFromCars(carEntity: CarEntity)

}