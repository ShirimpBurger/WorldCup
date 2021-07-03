package com.hbs.data.all.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hbs.data.local.database.LocalGameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Provides
    @Singleton
    fun provideSettingDatabase(
        @ApplicationContext context: Context
    ) : LocalGameDatabase =
        Room.databaseBuilder(
            context,
            LocalGameDatabase::class.java,
            "Setting.db"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("insert into SettingEntity (task, isUse) values ('Notification', 1)")
                db.execSQL("insert into SettingEntity (task, isUse) values ('Alternative', 1)")
            }
        }).build()
}

