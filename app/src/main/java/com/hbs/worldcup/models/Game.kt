package com.hbs.worldcup.models

typealias GamePairList = List<GamePair>

data class Game(
    val title: String,
    val thumbnail: String,
    val filed: String,
    val round: Int = 0,
    val updateAt: Long,
    val position: Int,
    val time: Long
)

data class GamePair(val firstGame: Game, val secondGame: Game)