package com.hbs.data.remote.mappers

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.hbs.data.local.model.GameEntities
import com.hbs.data.local.model.GameEntity
import java.lang.reflect.Type

fun String.toGameEntities(filed: String): List<GameEntity> {
    val gameEntityType = object : TypeToken<List<GameEntity>>() {}.type

    return GsonBuilder().registerTypeAdapter(gameEntityType, GameEntityDeserializer(filed))
        .create()
        .fromJson(this, gameEntityType)
}

class GameEntityDeserializer(private val filed: String) : JsonDeserializer<GameEntities> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GameEntities {
        return json?.asJsonArray?.map {
            val title = it.asJsonObject["name"].asString
            val thumbnail = it.asJsonObject["thumbnail"].asString
            GameEntity(title, thumbnail, filed)
        }?.toList() ?: emptyList()
    }
}