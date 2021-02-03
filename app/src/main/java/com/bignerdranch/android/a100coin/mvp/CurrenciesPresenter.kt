package com.bignerdranch.android.a100coin.mvp

import com.bignerdranch.android.a100coin.adapter.CurrenciesAdapter
import com.bignerdranch.android.a100coin.api.CoinGeckoApi
import com.bignerdranch.android.a100coin.contract.CurrenciesContract
import com.bignerdranch.android.a100coin.di.App
import com.bignerdranch.android.a100coin.formatThousands


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrenciesPresenter  : CurrenciesContract.Presenter() {


    @Inject
    lateinit var geckoApi: CoinGeckoApi // присоединяем данные

    init {
        App.appComponent.inject(this) // реализуем даггер
    }

    override fun makeList() {
        view.showProgress()

        subscribe(geckoApi.getCoinMarket() // создание реактиного потока
                .subscribeOn(Schedulers.io())  // подписка в фоновом потоке
                .observeOn(AndroidSchedulers.mainThread()) // наблюдатель в основном потоке
                .flatMap { Observable.fromIterable(it) } // из листа в обсервабл
                .doOnNext {
                    view.addCurrency(
                            CurrenciesAdapter.Currency (
                                it.id,
                                it.symbol,
                                it.name,
                                it.image,
                                it.current_price,
                                it.market_cap.formatThousands(),
                                it.market_cap_rank,
                                it.total_volume,
                                it.price_change_percentage_24h,
                                it.market_cap_change_percentage_24h,
                                it.circulating_supply,
                                it.total_supply,
                                it.ath,
                                it.ath_change_percentage
                            )
                    )
                }

                .doOnComplete {
                    view.hideProgress() // правильное завершение потока
                 }

                .subscribe({
                    view.hideProgress()
                    view.notifyAdapter() // подписаться на изменения
                }, {
                    view.showErrorMessage(it.message) // ищет и показывает ошибки
                    view.hideProgress()
                    it.printStackTrace()
                })


        )
    }


    override fun refreshList() {
        view.refresh()
        makeList()
    }


}