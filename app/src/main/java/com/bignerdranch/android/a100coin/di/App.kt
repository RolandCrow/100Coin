package com.bignerdranch.android.a100coin.di

import android.app.Application

class App : Application() { // для внедрения зависимостей


    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializerDagger()

    }

    private fun initializerDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .restModule(RestModule())
            .mvpModule(MvpModule())
            .chartModule(ChartModule())
            .build()
    }
}