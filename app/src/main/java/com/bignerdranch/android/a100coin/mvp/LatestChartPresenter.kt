package com.bignerdranch.android.a100coin.mvp

import com.bignerdranch.android.a100coin.api.CoinGeckoApi
import com.bignerdranch.android.a100coin.contract.LatestChartContract
import com.bignerdranch.android.a100coin.di.App

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import io.reactivex.Observable

class LatestChartPresenter : LatestChartContract.Presenter() {


    @Inject
    lateinit var geckoApi: CoinGeckoApi // получаем данные из api


    init {
        App.appComponent.inject(this) // инъекция
    }


    override fun makeChart(id: String) {
        subscribe(geckoApi.getCoinMarketChart(id)
            .map { it.prices }
            .flatMap { Observable.fromIterable(it) }
            .doOnComplete {
                view.hideProgress()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.addEntryToChart(it[0], it[1])
            }, {
                view.hideProgress()
                view.showErrorMessage(it.message)
                it.printStackTrace()
            })
        )
    }

    override fun refreshChart() {
        view.refresh()
    }

    // 10

}