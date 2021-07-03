package com.hbs.domain.usecase

import com.hbs.data.local.model.onHit
import com.hbs.data.local.model.onHitFailure
import com.hbs.data.local.repository.GameLocalRepository
import com.hbs.data.remote.model.ResultResponse
import com.hbs.data.remote.model.onFailure
import com.hbs.data.remote.model.onSuccess
import com.hbs.data.remote.repository.GameRemoteRepository
import com.hbs.data.remote.repository.GameResultResponse
import com.hbs.domain.mapper.toEntity
import com.hbs.domain.model.GameDomain
import com.hbs.domain.types.GameDataType
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GameDataUseCase {
    suspend fun getGameDatas(type: GameDataType): Flow<GameResultResponse>
    suspend fun pickGame(gameDomain: GameDomain)
}

class GameDataUseCaseImpl @Inject constructor(
    private val remoteRepository: GameRemoteRepository,
    private val localRepository: GameLocalRepository
) :
    GameDataUseCase {

    override suspend fun getGameDatas(type: GameDataType): Flow<GameResultResponse> =
        flow {
            when (type) {
                GameDataType.FOOD -> {
                    emit(ResultResponse.Loading)
                    val localCacheResult = localRepository.cache("foods")
                    localCacheResult
                        .onHit { emit(ResultResponse.Success(200, it)) }
                        .onHitFailure {
                            requestRemoteData(this)
                        }
                }
            }
        }

    override suspend fun pickGame(gameDomain: GameDomain) {
        coroutineScope {
            localRepository.update(gameDomain.apply { round += 1 }.toEntity())
        }
    }

    private suspend fun requestRemoteData(collector: FlowCollector<GameResultResponse>) {
        remoteRepository.requestGameResult("foods").collect { response ->
            response.onSuccess { collector.emit(ResultResponse.Success(200, it)) }
                .onFailure { error -> collector.emit(ResultResponse.Failure(Exception(error))) }
        }
    }
}