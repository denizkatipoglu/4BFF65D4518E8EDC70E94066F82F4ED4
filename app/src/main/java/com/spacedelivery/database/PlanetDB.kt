package com.spacedelivery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Deniz KATIPOGLU on 03,May,2021
 */
@Entity(tableName = "planet")
data class PlanetDB(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "coordinateX") var coordinateX: Double,
    @ColumnInfo(name = "coordinateY") var coordinateY: Double,
    @ColumnInfo(name = "capacity") var capacity: Int,
    @ColumnInfo(name = "stock") var stock: Int,
    @ColumnInfo(name = "need") var need: Int,
    @ColumnInfo(name = "eus") var eus: Double,
    @ColumnInfo(name = "favorite") var favorite: String

)