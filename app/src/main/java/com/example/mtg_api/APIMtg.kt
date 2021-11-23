package com.example.mtg_api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIMtg {
    @GET
    fun getCardByName(@Url url: String): Call<CardsResponse>
}