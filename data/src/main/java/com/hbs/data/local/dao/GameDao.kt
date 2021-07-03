package com.hbs.data.local.dao

import androidx.room.*
import com.hbs.data.local.model.GameEntities
import com.hbs.data.local.model.GameEntity

@Dao
interface GameDao {
    @Insert
    suspend fun insert(gameEntity: GameEntity)

    @Insert
    suspend fun insert(gameEntities: GameEntities)

    @Query("SELECT * FROM GameEntity WHERE filed = :filed")
    suspend fun getAllGameEntities(filed: String): GameEntities

    @Update
    suspend fun update(gameEntity: GameEntity)

    @Delete
    suspend fun delete(gameEntity: GameEntity)
}