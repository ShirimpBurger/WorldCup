package com.hbs.worldcup.mappers

import com.hbs.domain.model.SettingDomain
import com.hbs.worldcup.models.OneLineWithTaskItem

internal fun SettingDomain.toPresentation(): OneLineWithTaskItem {
    return OneLineWithTaskItem(
        task,
        isUse
    )
}