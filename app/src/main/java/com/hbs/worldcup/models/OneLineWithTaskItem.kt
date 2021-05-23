package com.hbs.worldcup.models

import com.hbs.worldcup.R
import com.hbs.worldcup.extensions.StingConfigs

data class OneLineWithTaskItem(
    val task: String,
    val isUse: Boolean
) {
    val title: String = getTitleText(isUse)

    val subtitle: String = getSubtitleText(isUse)

    val icon: Int = getImageIcon(task, isUse)

    private fun getTitleText(isUse: Boolean): String = if (isUse) {
        "Activate the $task"
    } else {
        "Inactivate the $task"
    }

    private fun getSubtitleText(isUse: Boolean) = if (isUse) {
        "Turn on"
    } else {
        "Turn off"
    }

    private fun getImageIcon(task: String, isUse: Boolean): Int {
        return when (task) {
            StingConfigs.NOTIFICATION -> {
                if(isUse) { R.drawable.ic_moon_color } else { R.drawable.ic_moon }
            }
            StingConfigs.ALTERNATIVE -> {
                if(isUse) { R.drawable.ic_moon_color } else { R.drawable.ic_moon }
            }
            else -> {
                if(isUse) { R.drawable.ic_moon_color } else { R.drawable.ic_moon }
            }
        }
    }
}