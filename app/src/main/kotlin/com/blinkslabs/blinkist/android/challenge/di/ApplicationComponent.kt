package com.blinkslabs.blinkist.android.challenge.di

import android.content.Context
import com.blinkslabs.blinkist.android.challenge.data.api.BooksApiModule
import com.blinkslabs.blinkist.android.challenge.ui.BooksActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BooksApiModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun inject(activity: BooksActivity)
}
