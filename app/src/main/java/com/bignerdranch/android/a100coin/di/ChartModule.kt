package com.bignerdranch.android.a100coin.di

import com.bignerdranch.android.a100coin.YearValueFormatter
import com.bignerdranch.android.a100coin.chart.LatestChart
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChartModule {

    @Provides
    @Singleton
    fun provideLatestChart() = LatestChart()



    @Provides
    @Singleton
    fun provideYearFormatter() = YearValueFormatter()
}