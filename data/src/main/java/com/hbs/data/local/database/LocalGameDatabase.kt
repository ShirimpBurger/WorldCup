package com.hbs.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
 import com.hbs.data.local.dao.GameDao
import com.hbs.data.local.dao.SettingDao
import com.hbs.data.local.model.GameEntity
import com.hbs.data.local.model.SettingEntity

@Database(entities = [SettingEntity::class, GameEntity::class], version = 1)
abstract class LocalGameDatabase : RoomDatabase(){
    abstract fun getSettingDao() : SettingDao
    abstract fun getGameDao() : GameDao
}