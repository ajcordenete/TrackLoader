package com.aljon.trackloader.di

import android.content.Context
import com.aljon.trackloader.TrackLoaderApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    RepositoryModule::class,
    FragmentBuilder::class,
    ViewModelBuilderModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun inject(app: TrackLoaderApplication)
}