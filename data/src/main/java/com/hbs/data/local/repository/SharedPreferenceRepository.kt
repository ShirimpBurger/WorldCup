package com.hbs.data.local.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

interface SharedPreferenceRepository {
    fun <T> get(key: String, defaultValue: T): T?
    fun <T> put(key: String, value: T)
}

class SharedPreferenceRepositoryImpl @Inject constructor(
    context: Context
) : SharedPreferenceRepository {
    private val FILE_NAME = "SECURITY"

    // this is equivalent to using deprecated MasterKeys.AES256_GCM_SPEC
    private val masterKey: MasterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun <T> get(key: String, defaultValue: T): T? {
        return when (defaultValue) {
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue as Float) as T
            is Int -> sharedPreferences.getInt(key, defaultValue as Int) as T
            is Long -> sharedPreferences.getLong(key, defaultValue as Long) as T
            is String -> sharedPreferences.getString(key, defaultValue as String) as T
            else -> {
                null
            }
        }
    }

    override fun <T> put(key: String, value: T) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Int -> editor.putInt(key, value)
        }
        editor.apply()
    }
}