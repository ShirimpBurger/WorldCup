package com.hbs.data.remote.model

import com.google.gson.annotations.SerializedName

typealias GameEntities = List<GameEntity>

data class GameEntity(@SerializedName("name") val title: String, val thumbnail: String)