package com.hbs.data.local.repository

import com.hbs.data.local.database.LocalGameDatabase
import com.hbs.data.local.model.DatabaseQuery
import com.hbs.data.local.model.DatabaseQueryConfig
import com.hbs.data.local.model.GameEntities
import com.hbs.data.local.model.GameEntity
import com.hbs.data.remote.repository.GameRepositoryConfig
import javax.inject.Inject

interface GameLocalRepository {
    suspend fun findAllData(filed: String): List<GameEntity>
    suspend fun insert(gameEntities: GameEntities)
    suspend fun delete(gameEntity: GameEntity)
    suspend fun cache(filed: String) : DatabaseQuery<GameEntities>
    suspend fun update(gameEntity: GameEntity)
}

class GameLocalRepositoryImpl @Inject constructor(private val database: LocalGameDatabase) : GameLocalRepository {
    private val gameDao = database.getGameDao()

    override suspend fun findAllData(filed: String): List<GameEntity> {
        return gameDao.getAllGameEntities(filed)
    }

    override suspend fun insert(gameEntities: GameEntities) {
        gameDao.insert(gameEntities)
    }

    override suspend fun delete(gameEntity: GameEntity) {
        gameDao.delete(gameEntity)
    }

    override suspend fun cache(filed: String): DatabaseQuery<GameEntities> {
        val gameEntities = database.getGameDao().getAllGameEntities(filed)
        return if (gameEntities.isEmpty()) {
            DatabaseQuery.Failure(DatabaseQueryConfig.EMPTY_SIZE_EXCEPTION)
        } else if (gameEntities.size != GameRepositoryConfig.REMOTE_DATA_SIZE) {
            DatabaseQuery.Failure(DatabaseQueryConfig.ILLEGAL_SIZE_EXCEPTION)
        } else if (gameEntities.isNotEmpty() && gameEntities[0].isNeedUpdate) {
            DatabaseQuery.Failure(DatabaseQueryConfig.TIME_OUT_EXCEPTION)
        } else {
            DatabaseQuery.Success(200, gameEntities)
        }
    }

    override suspend fun update(gameEntity: GameEntity) {
        gameDao.update(gameEntity)
    }
}