package com.bignerdranch.android.a100coin.di

import com.bignerdranch.android.a100coin.ChartActivity
import com.bignerdranch.android.a100coin.MainActivity
import com.bignerdranch.android.a100coin.chart.LatestChart
import com.bignerdranch.android.a100coin.fragments.CurrenciesListFragment
import com.bignerdranch.android.a100coin.mvp.CurrenciesPresenter
import com.bignerdranch.android.a100coin.mvp.LatestChartPresenter
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, RestModule::class, MvpModule::class, ChartModule::class]) // соединяем все вместе
@Singleton
interface AppComponent {
        fun inject(mainActivity: MainActivity) // передает все что есть в главную активность
        fun inject(presenter: CurrenciesPresenter)
        fun inject(presenter: LatestChartPresenter)
        fun inject(fragment: CurrenciesListFragment)
        fun inject(chart: LatestChart)
        fun inject(activity: ChartActivity)
}


