package com.example.projectforiterview.retrofit

import com.example.projectforiterview.retrofit.data.BillingEntryDetailsResponse
import com.example.projectforiterview.retrofit.data.BillingEntryRequest
import com.example.projectforiterview.retrofit.data.BillingHeaderListResponse
import com.example.projectforiterview.retrofit.data.BillingListRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BillingService {
    @POST("/payment/billing/entry/headers")
    fun getBillingHeaders(@Body request: BillingListRequest): Call<BillingHeaderListResponse>

    @POST("/payment/billing/entry/details")
    fun getBillingDetails(@Body request: BillingEntryRequest): Call<BillingEntryDetailsResponse>
}

