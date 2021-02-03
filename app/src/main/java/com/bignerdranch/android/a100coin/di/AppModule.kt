package com.bignerdranch.android.a100coin.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) { // доступ к ресурсам в любой части приложения

    @Provides
    @Singleton
    fun provideContext(): Context = app // предоставляет зависимости

}