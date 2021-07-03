package com.hbs.worldcup.mappers

import com.hbs.domain.model.GameDomain
import com.hbs.domain.model.SettingDomain
import com.hbs.worldcup.models.Game
import com.hbs.worldcup.models.OneLineWithTaskItem

internal fun OneLineWithTaskItem.toDomain(): SettingDomain {
    return SettingDomain(
        task,
        isUse
    )
}

internal fun Game.toDomain() : GameDomain {
    return GameDomain(title, thumbnail, filed, round, updateAt)
}