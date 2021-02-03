package com.bignerdranch.android.a100coin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.a100coin.ChartActivity
import com.bignerdranch.android.a100coin.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import java.util.*

class CurrenciesAdapter: BaseAdapter<CurrenciesAdapter.CurrencyViewHolder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesAdapter.CurrencyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false) // создаем холдер и передаем в него список
        return CurrencyViewHolder(v)
    }

    class CurrencyViewHolder(view: View): BaseAdapter.BaseViewHolder(view) { // реализация вьюхолдера
        var id: String = ""
        var symbol: String = ""
        var name: String = ""
        var image: String = ""
        var marketCap: String = ""
        var marketCapRank: Int = 0
        var marketCapChangePercentage24h: Float = 0.0f
        var priceChangePercentage24h: Float = 0.0f
        var totalVolume: Float = 0.0f
        var ath: Float = 0.0f
        var athChangePercentage: Float = 0.0f
        var circulatingSupply: Double = 0.0
        var totalSupply: Double = 0.0

        init {
            itemView.setOnClickListener {
                var intent = Intent(itemView.context, ChartActivity::class.java) // информация о криптовалюте в чарте
                intent.putExtra("id", id)
                    .putExtra("name", name)
                    .putExtra("symbol", symbol)
                    .putExtra("image", image)
                    .putExtra("marketCapRank", marketCapRank)
                    .putExtra("marketCap", marketCap)
                    .putExtra("marketCapChangePercentage24h", marketCapChangePercentage24h)
                    .putExtra("priceChangePercentage24h", priceChangePercentage24h)
                    .putExtra("totalVolume", totalVolume)
                    .putExtra("ath", ath)
                    .putExtra("athChangePercentage", athChangePercentage)
                    .putExtra("circulatingSupply", circulatingSupply)
                    .putExtra("totalSupply", totalSupply)
                itemView.context.startActivity(intent)
            }
        }

        override fun bind(item: Any) { // привязка к ресайклер вью и получение данных
            let {
                item as Currency
                Glide.with(view.context).load(item.image).into(view.ivCurrencyIcon) // загрузка изображения
                view.tvCurrencySym.text = item.symbol
                view.tvCurrencyName.text = item.name
                view.tvCurrencyMarketCap.text = item.marketCap
                view.tvCurrencyPrice.text = item.price.toString()
                id = item.id
                symbol = item.symbol
                name = item.name
                image = item.image
                marketCapRank = item.marketCapRank
                marketCapChangePercentage24h = item.marketCapChangePercentage24h
                priceChangePercentage24h = item.priceChangePercentage24h
                totalVolume = item.totalVolume
                ath = item.ath
                athChangePercentage = item.athChangePercentage
                circulatingSupply = item.circulatingSupply
                totalSupply = item.totalSupply
            }
        }




}

    data class Currency( // класс данных для элементов списка
        val id: String,
        val symbol: String,
        val name: String,
        val image: String,
        val price: Float,
        val marketCap: String,
        val marketCapRank: Int,
        val totalVolume: Float,
        val priceChangePercentage24h: Float,
        val marketCapChangePercentage24h: Float,
        val circulatingSupply: Double,
        val totalSupply: Double,
        val ath: Float,
        val athChangePercentage: Float
    )
}
