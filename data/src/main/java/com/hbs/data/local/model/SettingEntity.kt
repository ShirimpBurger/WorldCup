package com.hbs.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SettingEntity(
    @PrimaryKey
    val task: String,
    var isUse: Boolean
)