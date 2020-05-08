package com.aljon.trackloader.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.aljon.trackloader.data.local.TrackDao
import com.aljon.trackloader.data.local.TrackLoaderDatabase
import com.aljon.trackloader.data.remote.TrackApi
import com.aljon.trackloader.utils.Constants
import com.aljon.trackloader.utils.helper.LiveDataCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideTrackApi(retrofit: Retrofit): TrackApi {
        return retrofit.create(TrackApi::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): TrackLoaderDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TrackLoaderDatabase::class.java,
            "TrackLoader.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDao(database: TrackLoaderDatabase): TrackDao {
        return database.trackDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


}