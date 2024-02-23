package dev.jocey.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.jocey.data.data_source.local.LocalDataSource
import dev.jocey.data.data_source.local.LocalDataSourceImpl
import dev.jocey.data.data_source.local.NumberDao
import dev.jocey.data.data_source.local.RoomDB
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): RoomDB {
        return Room.databaseBuilder(context, RoomDB::class.java, "room_list_detail_db").build()
    }

    @Provides
    fun provideNumberDao(roomDB: RoomDB): NumberDao {
        return roomDB.numbersDao()
    }

    @Provides
    fun provideLocalDataSourceImpl(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource {
        return localDataSourceImpl
    }
}