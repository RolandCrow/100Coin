package com.bignerdranch.android.a100coin.contract

import com.bignerdranch.android.a100coin.adapter.CurrenciesAdapter

class CurrenciesContract {
   interface View: BaseContract.View {
       fun addCurrency(currency: CurrenciesAdapter.Currency) // добавляем валюту
       fun notifyAdapter() // оповещаем адаптер
       fun showProgress()
       fun hideProgress()
       fun showErrorMessage(error: String?)
       fun refresh() // перезапустить

   }

    abstract class Presenter: BaseContract.Presenter<View>() { // работа со списками
        abstract fun makeList()
        abstract fun refreshList()
    }


}