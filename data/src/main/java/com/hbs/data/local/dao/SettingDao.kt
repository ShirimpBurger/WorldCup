package com.hbs.data.local.dao

import androidx.room.*
import com.hbs.data.local.model.SettingEntity

@Dao
interface SettingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(settingItem: SettingEntity) : Long

    @Query("SELECT * FROM SettingEntity")
    suspend fun getAllSettingEntities(): List<SettingEntity>
}