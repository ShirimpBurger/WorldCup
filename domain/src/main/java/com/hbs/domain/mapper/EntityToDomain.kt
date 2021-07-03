package com.hbs.domain.mapper

import com.hbs.data.local.model.GameEntities
import com.hbs.data.local.model.GameEntity
import com.hbs.data.local.model.SettingEntity
import com.hbs.domain.model.GameDomain
import com.hbs.domain.model.GameDomains
import com.hbs.domain.model.SettingDomain

fun SettingEntity.toDomain() : SettingDomain {
    return SettingDomain(task, isUse)
}

fun GameEntity.toDomain() : GameDomain {
    return GameDomain(title, thumbnail, filed, round, updateAt)
}

fun GameEntities.toDomains() : GameDomains {
    return map { it.toDomain() }.toList()
}