package com.hbs.worldcup.mappers

import com.hbs.domain.model.SettingDomain
import com.hbs.worldcup.models.OneLineWithTaskItem

internal fun OneLineWithTaskItem.toDomain(): SettingDomain {
    return SettingDomain(
        task,
        isUse
    )
}