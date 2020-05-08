package com.aljon.trackloader.di

import com.aljon.trackloader.data.repository.session.SessionRepository
import com.aljon.trackloader.data.repository.session.SessionRepositoryImpl
import com.aljon.trackloader.data.repository.track.TrackRepository
import com.aljon.trackloader.data.repository.track.TrackRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: TrackRepositoryImpl): TrackRepository

    @Singleton
    @Binds
    abstract fun bindSessionRepository(repo: SessionRepositoryImpl): SessionRepository
}