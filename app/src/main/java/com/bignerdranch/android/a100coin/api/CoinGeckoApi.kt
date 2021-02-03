package com.bignerdranch.android.a100coin.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CoinGeckoApi {


    @GET("coins/markets")
    fun getCoinMarket(
        @Query("vs_currency") vs: String = "usd", // переменные остальных запросов
        @Query("per_page") perPage: Int = 100, // список валют
        @Query("sparkline") sparkline: Boolean   = false,
        @Query("order") order: String = "market_cap_desc"
    ): Observable<List<GeckoCoin>> // путь в дата класс через реактивную джаву


    @GET("coins/{id}/market_chart")
    fun getCoinMarketChart(
        @Path("id") id: String, // путь к переменной с подстановкой
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "max"
    ): Observable<GeckoCoinChart>
}

