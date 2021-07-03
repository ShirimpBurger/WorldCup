package com.hbs.domain.mapper

import com.hbs.data.local.model.GameEntity
import com.hbs.data.local.model.SettingEntity
import com.hbs.domain.model.GameDomain
import com.hbs.domain.model.SettingDomain

fun SettingDomain.toEntity() : SettingEntity{
    return SettingEntity(task, isUse)
}

fun GameDomain.toEntity() : GameEntity {
    return GameEntity(title, thumbnail, filed, round, updateAt)
}