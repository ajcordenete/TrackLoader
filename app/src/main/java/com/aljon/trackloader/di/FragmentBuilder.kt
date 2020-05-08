package com.aljon.trackloader.di

import com.aljon.trackloader.ui.trackdetail.TrackDetailFragment
import com.aljon.trackloader.ui.tracks.TracksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [TracksModule::class])
    abstract fun tracksFragment(): TracksFragment

    @ContributesAndroidInjector(modules = [TrackDetailModule::class])
    abstract fun trackDetailFragment(): TrackDetailFragment
}