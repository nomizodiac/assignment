package com.systems.assignment.common.di

import android.content.Context
import androidx.room.Room
import com.systems.assignment.common.data.local.AppDatabase
import com.systems.assignment.common.data.repositoryimp.DatabaseRepositoryImp
import com.systems.assignment.common.domain.repository.DatabaseRepository
import com.systems.assignment.common.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDatabaseRepository(
        appDatabase: AppDatabase
    ): DatabaseRepository = DatabaseRepositoryImp(appDatabase.accountDao())
}