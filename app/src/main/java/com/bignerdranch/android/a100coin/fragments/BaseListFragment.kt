package com.bignerdranch.android.a100coin.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.a100coin.adapter.BaseAdapter
import kotlinx.android.synthetic.main.fragment_currencies_list.*



abstract class BaseListFragment : Fragment() { // отображаем список криптовалют

    private lateinit var recyclerView: RecyclerView
    protected lateinit var viewAdapter: BaseAdapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context) // присоединяем вью
        viewAdapter = createAdapterInstance() // присоединяя адаптер

        recyclerView = list.apply {
            setHasFixedSize(true) // передаем данные в точный размер окна
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    abstract fun createAdapterInstance(): BaseAdapter<*>



}