package com.aljon.trackloader.di

import androidx.lifecycle.ViewModel
import com.aljon.trackloader.ui.tracks.TracksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TracksModule {

    @Binds
    @IntoMap
    @ViewModelKey(TracksViewModel::class)
    abstract fun bindViewModel(viewModel: TracksViewModel) : ViewModel
}