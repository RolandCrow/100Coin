package com.bignerdranch.android.a100coin.contract

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseContract { // абстрактный презентер для базовых действий с вью

    interface View

    abstract class Presenter<V: View> {
        private val subscriptions = CompositeDisposable() // подписка
        protected lateinit var view: V


        fun subscribe(subscription: Disposable) {
            subscriptions.add(subscription)
        }

        fun unsubscribe() {
            subscriptions.clear()
        }

        fun attach(view: V) {
            this.view = view
        }

        fun detach() {
            unsubscribe()
        }


    }



}