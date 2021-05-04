package com.spacedelivery.database

import androidx.room.*

/**
 * Created by Deniz KATIPOGLU on 03,May,2021
 */
@Dao
interface TodoDao {
    @Query("SELECT * FROM spaceChar")
    fun getAll(): List<SpaceName>

    @Query("SELECT * FROM spaceChar WHERE spaceName LIKE :spaceName")
    fun findByTitle(spaceName: String): SpaceName

    @Insert
    fun insertAll(vararg todo: SpaceName)

    @Delete
    fun delete(todo: SpaceName)

    @Update
    fun updateTodo(vararg todos: SpaceName)

//    TODO PLANET

    @Query("SELECT * FROM planet")
    fun getAllPlanet(): List<PlanetDB>

    @Query("SELECT * FROM planet WHERE name LIKE :spaceName")
    fun findByTitlePlanet(spaceName: String): PlanetDB

    @Query("SELECT * FROM planet WHERE favorite LIKE :spaceName")
    fun findByFavoritePlanet(spaceName: Boolean): PlanetDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlanet(vararg todo: PlanetDB)


    @Delete
    fun deletePlanet(todo: PlanetDB)

    @Update
    fun updateTodoPlanet(vararg todos: PlanetDB)
}