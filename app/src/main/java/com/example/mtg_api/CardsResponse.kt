package com.example.mtg_api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CardsResponse (@SerializedName("cards") var cards: List<Card>)

data class Card(
    @SerializedName("name")
    var name: String?,

    @SerializedName("text")
    var text: String?,

    @SerializedName("type")
    var type: String?,

    @SerializedName("manaCost")
    var manaCost: String?,

    @SerializedName("setName")
    var set: String?,

    @SerializedName("imageUrl")
    var image: String?,

    @SerializedName("foreignNames")
    var foreignNames: List<CardTranslation>
)

data class CardTranslation(
    @SerializedName("name")
    var name: String?,

    @SerializedName("text")
    var text: String?,

    @SerializedName("type")
    var type: String?,

    @SerializedName("imageUrl")
    var imageUrl: String?,

    @SerializedName("language")
    var language: String?,
) : Serializable