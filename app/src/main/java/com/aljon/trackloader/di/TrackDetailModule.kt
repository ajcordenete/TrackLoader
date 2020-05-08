package com.aljon.trackloader.di

import androidx.lifecycle.ViewModel
import com.aljon.trackloader.ui.trackdetail.TrackDetailViewModel
import com.aljon.trackloader.ui.tracks.TracksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TrackDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrackDetailViewModel::class)
    abstract fun bindViewModel(viewModel: TrackDetailViewModel): ViewModel
}