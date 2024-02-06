package com.example.projectforiterview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectforiterview.retrofit.BillingService
import com.example.projectforiterview.retrofit.data.BillingEntryDetails
import com.example.projectforiterview.retrofit.data.BillingEntryDetailsResponse
import com.example.projectforiterview.retrofit.data.BillingEntryHeader
import com.example.projectforiterview.retrofit.data.BillingEntryRequest
import com.example.projectforiterview.retrofit.data.BillingHeaderListResponse
import com.example.projectforiterview.retrofit.data.BillingListRequest
import com.example.projectforiterview.retrofit.maneger.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FragmentsViewModel : ViewModel() {

    private val listDataItemMutableLiveData = MutableLiveData<List<BillingEntryHeader>>()
    private val dataItemMutableLiveData = MutableLiveData<BillingEntryDetails>()


    val listDataItemLiveData: LiveData<List<BillingEntryHeader>>
        get() = listDataItemMutableLiveData

    val dataItemLiveData: LiveData<BillingEntryDetails>
        get() = dataItemMutableLiveData


    fun requestListData() {
        viewModelScope.launch(Dispatchers.IO) {
            requestListDatFromNetwork()
        }
    }

    fun requestDetailsData(id : Long) {
        viewModelScope.launch(Dispatchers.IO) {
            requestDetailsDatFromNetwork(id)
        }
    }

    private fun requestListDatFromNetwork() {
        val billingService = RetrofitManager.getInstance().create(BillingService::class.java)
        val call = billingService.getBillingHeaders(BillingListRequest())
        call.enqueue(object : retrofit2.Callback<BillingHeaderListResponse> {
            override fun onResponse(call: retrofit2.Call<BillingHeaderListResponse>, response: Response<BillingHeaderListResponse>
            ) {
                if (response.isSuccessful) {
                    val headers = response.body()?.headers
                    listDataItemMutableLiveData.postValue(headers)
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: retrofit2.Call<BillingHeaderListResponse>, t: Throwable) {
            }

        })

    }

    private fun requestDetailsDatFromNetwork(id: Long) {
        val billingService = RetrofitManager.getInstance().create(BillingService::class.java)
        val call = billingService.getBillingDetails(BillingEntryRequest(id))
        call.enqueue(object : retrofit2.Callback<BillingEntryDetailsResponse> {
            override fun onResponse(call: retrofit2.Call<BillingEntryDetailsResponse>, response: Response<BillingEntryDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    val headers = response.body()?.details
                    dataItemMutableLiveData.postValue(headers)
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: retrofit2.Call<BillingEntryDetailsResponse>, t: Throwable) {
            }

        })



    }

}