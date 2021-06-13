package com.hbs.worldcup.models

typealias GameLayoutPairList = List<GameLayoutPair>
data class GameLayout(val title: String?, val thumbnail: String?)
data class GameLayoutPair(val firstGameLayout: GameLayout, val secondGameLayout: GameLayout)