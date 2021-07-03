package com.hbs.domain.model

typealias GameDomains = List<GameDomain>

data class GameDomain(
    val title: String,
    val thumbnail: String,
    val filed: String,
    var round: Int = 0,
    val updateAt: Long
)