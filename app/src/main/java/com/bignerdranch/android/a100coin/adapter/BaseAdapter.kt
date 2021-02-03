package com.bignerdranch.android.a100coin.adapter

import android.content.ClipData
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<VH: BaseAdapter.BaseViewHolder>: RecyclerView.Adapter<VH> () { // создаем адаптер для фрагмента


    var items: ArrayList<Any> = ArrayList() // список элементов



    override fun getItemCount(): Int { // размер списка
       return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) { // связка вью и содержимое
        holder.bind(getItem(position))
    }


    fun getItem(position: Int): Any {
        return items[position]
    }

    fun add(newItem: Any) { // добавляем одно
        items.add(newItem)
    }

    fun add(newItems: List<Any>) { // добавляем множество
        items.addAll(newItems)
    }


    abstract class BaseViewHolder(protected val view: View): RecyclerView.ViewHolder(view) {
            abstract fun bind(item: Any)
    }
}


