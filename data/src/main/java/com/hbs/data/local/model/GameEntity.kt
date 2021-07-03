package com.hbs.data.local.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

typealias GameEntities = List<GameEntity>

@Entity
data class GameEntity(
    val title: String,
    val thumbnail: String,
    val filed: String,
    val round: Int = 0,
    val updateAt: Long = Date().time,
    @PrimaryKey(autoGenerate = true) var id: Int? = 0
){
    @Ignore
    val isNeedUpdate = Date().time - updateAt > 60000
}