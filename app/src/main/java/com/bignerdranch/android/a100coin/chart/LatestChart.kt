package com.bignerdranch.android.a100coin.chart

import android.content.Context
import android.graphics.Color
import com.bignerdranch.android.a100coin.R
import com.bignerdranch.android.a100coin.YearValueFormatter
import com.bignerdranch.android.a100coin.di.App

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

import javax.inject.Inject

class LatestChart {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var formatter: YearValueFormatter


    lateinit var chart: LineChart

    init {
        App.appComponent.inject(this)
    }

    fun initChart(chart: LineChart) {
        this.chart = chart


        chart.description.isEnabled = false // убираем описание

        chart.isDragEnabled = true // убираес масштабирование
        chart.setScaleEnabled(false)
        chart.isScaleXEnabled = true
        chart.setDrawGridBackground(false)
        chart.isDoubleTapToZoomEnabled = false

        chart.setPinchZoom(false) // только по оси икс и игорек

        chart.maxHighlightDistance = 300F // максимальный размер на экране устройства

        val data = LineData()
        data.setValueTextColor(
            Color.BLACK
        )
        chart.data = data // пустая дата
        chart.legend.isEnabled =  true

        // добавляем маркер
        chart.setDrawMarkers(true)
        chart.marker =
            MyMarkerView(context, R.layout.custom_marker_view)

        val x1 = chart.xAxis // ось икс
        x1.textColor = Color.BLACK
        x1.position = XAxis.XAxisPosition.BOTTOM
        x1.setDrawGridLines(false)
        x1.valueFormatter = formatter
        x1.labelCount = 3
        x1.granularity = 48F

        x1.setAvoidFirstLastClipping(true)
        x1.isEnabled = true

        val leftAxis = chart.axisLeft // левая ось
        leftAxis.textColor = Color.BLACK
        leftAxis.setDrawGridLines(true)

        val rightAxis = chart.axisRight
        rightAxis.isEnabled = true

    }



    // добавление данных на график
    fun addEntry(value: Float, date: Float) {
        val data = chart.data

        if(data != null) {
            var set: ILineDataSet? = data.getDataSetByIndex(0)

            if(set == null) {
                set  = createSet()
                data.addDataSet(set)
            }

            data.addEntry(Entry(date, value), 0)
            data.notifyDataChanged()

            chart.notifyDataSetChanged()

            chart.moveViewToX(date)
            chart.highlightValue(value, 0)

        }

    }

// создание и настройка набора данных
    private fun createSet(): LineDataSet {
        val set = LineDataSet(null, "Price USD")
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.cubicIntensity = 0.2f
        set.setDrawFilled(true) // круг заполнен
        set.setDrawCircles(false)
        set.lineWidth = 1.8f
        set.setCircleColor(Color.BLACK)
        set.highlightLineWidth = 1.2f
        set.highLightColor = context.resources.getColor(R.color.colorAccent)
        set.color = Color.BLACK
        set.fillColor = Color.BLACK
        set.enableDashedHighlightLine(10f,5f, 0f)
        set.fillAlpha = 100
        set.setDrawHorizontalHighlightIndicator(true)
        set.setFillFormatter {
            _,_ ->
            chart.axisLeft.axisMaximum
        }
    return set
    }
// 6








}