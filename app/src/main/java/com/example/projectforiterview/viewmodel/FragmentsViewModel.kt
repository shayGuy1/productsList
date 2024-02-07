package com.example.projectforiterview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectforiterview.retrofit.ProductsService
import com.example.projectforiterview.retrofit.data.Product
import com.example.projectforiterview.retrofit.data.ProductsResponse
import com.example.projectforiterview.retrofit.maneger.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class FragmentsViewModel : ViewModel() {

    private val listDataItemMutableLiveData = MutableLiveData<List<Product>>()


    val listDataItemLiveData: LiveData<List<Product>>
        get() = listDataItemMutableLiveData


    fun requestListData() {
        viewModelScope.launch(Dispatchers.IO) {
            requestListDatFromNetwork()
        }
    }

    private fun requestListDatFromNetwork() {
        val billingService = RetrofitManager.getInstance().create(ProductsService::class.java)
        val call = billingService.getProducts(100)
        call.enqueue(object  : retrofit2.Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {

                if (response.isSuccessful) {
                    val headers = response.body()?.products
                    listDataItemMutableLiveData.postValue(headers)
                } else {
                    // Handle unsuccessful response
                }            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}