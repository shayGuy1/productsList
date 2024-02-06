package com.example.projectforiterview.retrofit.data

import com.google.gson.annotations.SerializedName

class BillingEntryRequest(
    @SerializedName("billingId")
    val billingId: Long
)