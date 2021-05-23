package com.hbs.domain.usecase

import com.hbs.data.local.repository.SettingRepository
import com.hbs.domain.mapper.toDomain
import com.hbs.domain.mapper.toEntity
import com.hbs.domain.model.SettingDomain
import javax.inject.Inject

typealias SettingDomains = List<SettingDomain>

interface SettingUseCase {
    suspend fun insertToggleData(settingDomain: SettingDomain) : Long
    suspend fun getAllSettingItems(): SettingDomains
}

class SettingUseCaseImpl @Inject constructor(val repository: SettingRepository) : SettingUseCase{
    override suspend fun insertToggleData(settingDomain: SettingDomain): Long {
        val entity = settingDomain.toEntity().apply {
            isUse = !isUse
        }
        return repository.insert(entity)
    }

    override suspend fun getAllSettingItems(): SettingDomains {
        return repository.getAllSettingEntities().map { it.toDomain() }.sortedByDescending { it.task }
    }
}