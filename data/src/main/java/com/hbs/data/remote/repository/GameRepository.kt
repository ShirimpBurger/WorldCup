package com.hbs.data.remote.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.gson.Gson
import com.hbs.data.remote.mappers.toGameEntity
import com.hbs.data.remote.model.GameEntity
import com.hbs.data.remote.model.ResultResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

typealias GameEntityResults = ResultResponse<List<GameEntity>>
interface GameRepository {
    fun requestGameEntities(collection: String, field: String): Flow<GameEntityResults>
}

class GameRepositoryImpl @Inject constructor() : GameRepository {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun requestGameEntities(collection: String, filed: String): Flow<GameEntityResults> {
        val foodDocument = db
            .collection(collection)
            .document("RnIAPgFbLd31WWo4pYTs")

        return callbackFlow {
            offer(ResultResponse.Loading)
            foodDocument
                .get(Source.DEFAULT)
                .addOnSuccessListener { snapshot ->
                    val foods = snapshot.get(filed)
                    if (foods == null) {
                        offer(ResultResponse.Failure(Exception("Load Failed")))
                    } else {
                        val gson = Gson()
                        offer(ResultResponse.Success(200, gson.toJson(foods).toGameEntity()))
                    }
                }
                .addOnFailureListener {
                    offer(ResultResponse.Failure(it))
                }
            awaitClose { close() }
        }
    }
}