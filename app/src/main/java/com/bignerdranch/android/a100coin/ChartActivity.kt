package com.bignerdranch.android.a100coin

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.a100coin.chart.LatestChart
import com.bignerdranch.android.a100coin.contract.LatestChartContract
import com.bignerdranch.android.a100coin.di.App
import com.bignerdranch.android.a100coin.mvp.LatestChartPresenter
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_chart.*
import java.text.DecimalFormat
import javax.inject.Inject

class ChartActivity : AppCompatActivity(), OnChartValueSelectedListener, LatestChartContract.View {

    // присоединяем код к активити

    @Inject
    lateinit var latestChart: LatestChart // присоединяем через даггер


    @Inject
    lateinit var presenter: LatestChartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart) // присоединяем активити
        App.appComponent.inject(this)
        presenter.attach(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("name") // присоединяем макет через интенты к переменныи
        val marketCapRank = intent.getIntExtra("marketCapRank", 0)
        val symbol = intent.getStringExtra("symbol")
        val marketCap = intent.getStringExtra("marketCap")
        val marketCapChangePercentage24h =
            intent?.getFloatExtra("marketCapChangePercentage24h", 0.0f)
        val priceChangePercentage24h = intent?.getFloatExtra("priceChangePercentage24h", 0.0f)
        val totalVolume = intent.getFloatExtra("totalVolume", 0.0f)
        val ath =  intent?.getFloatExtra("ath", 0.0f)
        val athChangePercentage = intent?.getFloatExtra("athChangePercentage", 0.0f)
        val circulatingSupply = intent?.getDoubleExtra("circulatingSupply", 0.0)
        val totalSupply = intent?.getDoubleExtra("totalSupply", 0.0)
        val image = intent.getStringExtra("image")

        Glide.with(this).load(image).into(ivCurrencyDetailIcon) // загрузка изображения
        supportActionBar?.title = name

        val df = DecimalFormat("#")
        df.maximumFractionDigits = 2

        tvDetailMarketCapRank.text = marketCapRank.toString()
        tvMarketCapChange.text = marketCapChangePercentage24h.toString()
        tvATH.text = ath.toString()
        tvAthChange.text = df.format(athChangePercentage)
        tvCirculatingSupply.text = df.format(circulatingSupply)
        tvTotalSupply.text = totalSupply.toString() // соединяем все переменные для отображения

        intent?.getStringExtra("id")?.let { presenter.makeChart(it) }
        latestChart.initChart(chartCurrency)






    }

    override fun onNothingSelected() {
        TODO("Not yet implemented")
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("Not yet implemented")
    }

    override fun addEntryToChart(value: Float, date: String) {
        TODO("Not yet implemented")
    }

    override fun addEntryToChart(date: Float, value: Float) {
        latestChart.addEntry(value, date)
    }

    override fun showProgress() {
        progressChart.visibility = View.VISIBLE // видимо
    }

    override fun hideProgress() {
        progressChart.visibility = View.INVISIBLE // невидимо
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show() // делаем тост
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this) // продолжение процесса
    }

    override fun onPause() {
        super.onPause()
        presenter.detach() // пауза
    }





}