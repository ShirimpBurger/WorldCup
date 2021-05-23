package com.hbs.data.local.repository

import com.hbs.data.local.database.SettingDatabase
import com.hbs.data.local.model.SettingEntity
import javax.inject.Inject

typealias SettingEntities = List<SettingEntity>

interface SettingRepository {
    suspend fun insert(settingEntity: SettingEntity) : Long
    suspend fun getAllSettingEntities(): SettingEntities
}

class SettingRepositoryImpl @Inject constructor(database: SettingDatabase) : SettingRepository {
    private val dao = database.getSettingDao()
    override suspend fun insert(settingEntity: SettingEntity) : Long {
        return dao.insert(settingEntity)
    }

    override suspend fun getAllSettingEntities(): SettingEntities {
        return dao.getAllSettingEntities()
    }
}