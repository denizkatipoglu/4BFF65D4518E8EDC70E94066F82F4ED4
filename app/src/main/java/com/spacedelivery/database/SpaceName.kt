package com.spacedelivery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Deniz KATIPOGLU on 03,May,2021
 */
@Entity(tableName = "spaceChar")
data class SpaceName(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "spaceName") var spaceName: String,
    @ColumnInfo(name = "capacity") var capacity: Float,
    @ColumnInfo(name = "durability") var durability: Float,
    @ColumnInfo(name = "pace") var pace: Float,
    @ColumnInfo(name = "damage") var damage: Int
)