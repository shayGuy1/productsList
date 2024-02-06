package com.example.projectforiterview.retrofit.data

import com.google.gson.annotations.SerializedName

class BillingEntryHeader(
    @SerializedName("id")
    val id: Long,

    @SerializedName("price")
    val price: Double,

    @SerializedName("created")
    val created: Long,

    @SerializedName("entryNumber")
    val entryNumber: Int,

    @SerializedName("val")
    val value: Int,

    @SerializedName("source")
    val source: String,

    @SerializedName("currencyCode")
    val currencyCode: String,

    @SerializedName("cardType")
    val cardType: String
)