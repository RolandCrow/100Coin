package com.bignerdranch.android.a100coin.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.bignerdranch.android.a100coin.R
import com.bignerdranch.android.a100coin.dateToString
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.custom_marker_view.view.*

@SuppressLint("ViewConstructor")
class MyMarkerView(context: Context, layoutResources: Int)
    : MarkerView(context, layoutResources)

{
    private val tvContent: TextView

    init {
        tvContent = findViewById(R.id.tvContent)
    }

    @SuppressLint("SetTextI18n")
    override fun refreshContent(e: Entry, highlight: Highlight?) {
        tvContent.text = e.y.toString() + "\n" + e.x.dateToString("MMM dd, yyyy") // обновление
        // графика при каждой загрузке с координатами

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width/2)).toFloat(), (-height).toFloat()) // возвращаем положение
        // маркера
    }



}