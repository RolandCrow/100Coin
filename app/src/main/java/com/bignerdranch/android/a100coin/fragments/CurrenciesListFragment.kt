package com.bignerdranch.android.a100coin.fragments

import android.icu.util.ValueIterator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


import com.bignerdranch.android.a100coin.R
import com.bignerdranch.android.a100coin.adapter.BaseAdapter
import com.bignerdranch.android.a100coin.adapter.CurrenciesAdapter
import com.bignerdranch.android.a100coin.contract.CurrenciesContract
import com.bignerdranch.android.a100coin.di.App
import com.bignerdranch.android.a100coin.mvp.CurrenciesPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CurrenciesListFragment : BaseListFragment(), CurrenciesContract.View {


    @Inject
    lateinit var presenter: CurrenciesPresenter // подклключает к реактивному потоку

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currencies_list, container, false) // связка фрагментов
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.attach(this)
        presenter.makeList()
    }




    override fun createAdapterInstance(): BaseAdapter<*> {
        return CurrenciesAdapter()
    }

    override fun addCurrency(currency: CurrenciesAdapter.Currency) {
        viewAdapter.add(currency) // через базовый фрагмент
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun showProgress() { // переопределяем функции заложенные в базовом контракте
        requireActivity().progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        requireActivity().progress.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }


    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }









}