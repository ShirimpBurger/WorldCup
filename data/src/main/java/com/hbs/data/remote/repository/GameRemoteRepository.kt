package com.hbs.data.remote.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.gson.Gson
import com.hbs.data.local.model.GameEntities
import com.hbs.data.local.model.GameEntity
import com.hbs.data.remote.mappers.toGameEntities
import com.hbs.data.remote.model.ResultResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

typealias GameResultResponse = ResultResponse<List<GameEntity>>

object GameRepositoryConfig {
    const val REMOTE_DATA_SIZE = 16
}

interface GameRemoteRepository {
    fun requestGameResult(field: String): Flow<GameResultResponse>
}

class GameRemoteRepositoryImpl @Inject constructor() : GameRemoteRepository {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun requestGameResult(filed: String): Flow<GameResultResponse> {
        return callbackFlow {
            offer(ResultResponse.Loading)
            when (filed) {
                "foods" -> requestFoodData(filed, channel)
            }
            awaitClose { close() }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun requestFoodData(
        filed: String,
        channel: SendChannel<ResultResponse<GameEntities>>
    ) = coroutineScope {
        val foodDocument = db
            .collection(filed)
            .document("RnIAPgFbLd31WWo4pYTs")

        foodDocument
            .get(Source.DEFAULT)
            .addOnSuccessListener { snapshot ->
                val foods = snapshot.get(filed)
                if (foods == null) {
                    channel.offer(ResultResponse.Failure(Exception("Load Failed")))
                } else {
                    val gameEntities = Gson().toJson(foods).toGameEntities(filed)
                    channel.offer(ResultResponse.Success(200, gameEntities))
                }
            }
            .addOnFailureListener {
                channel.offer(ResultResponse.Failure(it))
            }
    }
}