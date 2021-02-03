package com.bignerdranch.android.a100coin.di

import com.bignerdranch.android.a100coin.mvp.CurrenciesPresenter
import com.bignerdranch.android.a100coin.mvp.LatestChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule  {

    @Provides
    @Singleton
    fun provideCurrenciesPresenter(): CurrenciesPresenter = CurrenciesPresenter()

    @Provides
    @Singleton
    fun provideLatestChartPresenter(): LatestChartPresenter = LatestChartPresenter()

}