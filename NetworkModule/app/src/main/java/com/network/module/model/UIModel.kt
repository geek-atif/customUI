package com.network.module.model

import com.google.gson.annotations.SerializedName

data class UIModel(
    @SerializedName("heading-text")
    val heading_text: String,
    @SerializedName("logo-url")
    val logo_url: String,
    @SerializedName("uidata")
    val uidata: List<Uidata>
)