package com.hbs.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hbs.data.local.dao.SettingDao
import com.hbs.data.local.model.SettingEntity

@Database(entities = [SettingEntity::class], version = 1)
abstract class SettingDatabase : RoomDatabase(){
    abstract fun getSettingDao() : SettingDao
}