package com.example.projectforiterview.retrofit.data

import com.google.gson.annotations.SerializedName

class BillingHeaderListResponse(
    @SerializedName("headers")
    val headers: List<BillingEntryHeader>
)