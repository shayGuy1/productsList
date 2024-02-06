package com.example.projectforiterview.retrofit.data

import com.google.gson.annotations.SerializedName

class BillingEntryDetailsResponse(
    @SerializedName("details")
    val details: BillingEntryDetails
)