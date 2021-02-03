package com.bignerdranch.android.a100coin

import java.text.SimpleDateFormat
import java.util.*


fun Float.formatThousands(): String {
    val sb = StringBuilder()
    val formatter = Formatter(sb, Locale.US) // определяем числа
    formatter.format("%(.0f,", this)
    return sb.toString()

}

fun Number.dateToString(pattern: String): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.toLong()
    return SimpleDateFormat(pattern).format(calendar.time) // определяем точную дату

}