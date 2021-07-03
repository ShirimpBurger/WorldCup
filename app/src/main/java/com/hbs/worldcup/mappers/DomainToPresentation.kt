package com.hbs.worldcup.mappers

import com.hbs.domain.model.GameDomain
import com.hbs.domain.model.GameDomains
import com.hbs.worldcup.models.Game
import com.hbs.worldcup.models.GamePair
import com.hbs.domain.model.SettingDomain
import com.hbs.worldcup.models.OneLineWithTaskItem

internal fun SettingDomain.toPresentation(): OneLineWithTaskItem {
    return OneLineWithTaskItem(
        task,
        isUse
    )
}

internal fun GameDomain.toPresentation(): Game {
    return Game(title, thumbnail, filed, round, updateAt, -1, -99999999)
}

internal fun GameDomains.toWindowed(): List<GamePair> {
    return windowed(2, 2) { gameEntities ->
        GamePair(gameEntities[0].toPresentation(), gameEntities[1].toPresentation())
    }
}