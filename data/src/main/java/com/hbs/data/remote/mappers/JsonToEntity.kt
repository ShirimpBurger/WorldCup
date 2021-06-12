package com.hbs.data.remote.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hbs.data.remote.model.GameEntity

fun String.toGameEntity() : List<GameEntity> {
    val gameEntityType = object : TypeToken<List<GameEntity>>() {}.type
    return Gson().fromJson(this, gameEntityType)
}