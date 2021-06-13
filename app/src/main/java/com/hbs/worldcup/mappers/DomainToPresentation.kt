package com.hbs.worldcup.mappers

import com.hbs.data.remote.model.GameEntities
import com.hbs.data.remote.model.GameEntity
import com.hbs.worldcup.models.GameLayout
import com.hbs.worldcup.models.GameLayoutPair
import com.hbs.domain.model.SettingDomain
import com.hbs.worldcup.models.OneLineWithTaskItem

internal fun SettingDomain.toPresentation(): OneLineWithTaskItem {
    return OneLineWithTaskItem(
        task,
        isUse
    )
}

internal fun GameEntity.toPresentation(): GameLayout {
    return GameLayout(title, thumbnail)
}

internal fun GameEntities.toWindowed(): List<GameLayoutPair> {
    return windowed(2, 2) { gameEntities ->
        GameLayoutPair(gameEntities[0].toPresentation(), gameEntities[1].toPresentation())
    }
}

