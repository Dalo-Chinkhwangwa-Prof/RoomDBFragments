package com.example.myroomdbapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cars")
class CarEntity(
    @PrimaryKey(autoGenerate = true) var carId: Int?,
    @ColumnInfo(name = "carName") val carName: String,
    @ColumnInfo(name = "carModel") val carModel: String,
    @ColumnInfo(name = "carPrice") val carPrice: Int
) {
    constructor(carName: String, carModel: String, carPrice: Int) : this(null, carName, carModel, carPrice)
}