package com.hbs.domain.model

data class AlarmItem(val title: String, val subtitle: String) {
    val firstWord = title.substring(0, 1)
}