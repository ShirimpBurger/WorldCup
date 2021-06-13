package com.hbs.domain.usecase

import com.hbs.data.remote.repository.GameEntityResults
import com.hbs.data.remote.repository.GameRepository
import com.hbs.domain.types.GameDataType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GameDataUseCase {
    suspend fun getAllData(type: GameDataType): Flow<GameEntityResults>
}

class GameDataUseCaseImpl @Inject constructor(private val repository: GameRepository) :
    GameDataUseCase {

    override suspend fun getAllData(type: GameDataType): Flow<GameEntityResults> {
        when (type) {
            GameDataType.FOOD -> {
                return repository.requestGameEntities("foods", "foods")
            }
        }
    }
}