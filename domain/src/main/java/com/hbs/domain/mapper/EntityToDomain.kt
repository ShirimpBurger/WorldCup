package com.hbs.domain.mapper

import com.hbs.data.local.model.SettingEntity
import com.hbs.domain.model.SettingDomain

fun SettingEntity.toDomain() : SettingDomain {
    return SettingDomain(task, isUse)
}