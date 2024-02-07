package com.example.mystore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.net.ssl.HttpsURLConnection
import com.example.mystore.data.Category
import com.example.mystore.api.ProductsService
import com.example.mystore.api.data.ProductWS
import com.example.mystore.api.data.ProductsResponse
import com.example.mystore.api.maneger.RetrofitManager
import com.example.mystore.application.App
import com.example.mystore.data.Product
import com.example.mystore.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class CategoryFragmentViewModel : ViewModel() {

    companion object {
        private const val PRODUCTS_MAX_COUNT = 100
    }

    private val listDataItemMutableLiveData = MutableLiveData<List<Category>>()
    private val loadingErrorMessageMutableLiveData = MutableLiveData<String>()
    private val loadingStatusMutableLiveData = MutableLiveData<Boolean>()

    val listDataItemLiveData: LiveData<List<Category>>
        get() = listDataItemMutableLiveData

    val loadingErrorMessageLiveData: LiveData<String>
        get() = loadingErrorMessageMutableLiveData

    val loadingStatusLiveData: LiveData<Boolean>
        get() = loadingStatusMutableLiveData

    fun requestListData() {
        viewModelScope.launch(Dispatchers.IO) {
            requestProducts()
        }
    }

    private fun requestProducts() {
        loadingStatusMutableLiveData.postValue(true)
        val billingService = RetrofitManager.getInstance().create(ProductsService::class.java)
        val call = billingService.getProducts(PRODUCTS_MAX_COUNT)
        call.enqueue(object : retrofit2.Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                loadingStatusMutableLiveData.postValue(false)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val products = it.products
                        val categories = createCategories(products)
                        listDataItemMutableLiveData.postValue(categories)
                    } ?: {
                        // Handle empty body error
                        loadingErrorMessageMutableLiveData.postValue("Server response returned with empty body")
                    }
                } else { // On response failed
                    if (response.code() != HttpsURLConnection.HTTP_OK) {
                        loadingErrorMessageMutableLiveData.postValue("HTTP response error - " + response.code())
                    } else {
                        loadingErrorMessageMutableLiveData.postValue("Failed to load products data from the server")
                    }
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                loadingStatusMutableLiveData.postValue(false)
                if (NetworkUtils.isOffline(App.getContext())) {
                    loadingErrorMessageMutableLiveData.postValue("Device is offline. Please check your internet settings and try again")
                } else {
                    loadingErrorMessageMutableLiveData.postValue(t.localizedMessage)
                }
            }
        })
    }

    private fun createCategories(products: List<ProductWS>): List<Category> {
        val categories = HashMap<String, Category>()

        products.forEach { product ->
            // product's category is not exist in the map yet...
            if (!categories.containsKey(product.category)) {
                categories[product.category] = Category(
                    name = product.category,
                    imageUrl = product.thumbnail
                )
            }

            // add the new product to the category's products list
            categories[product.category]?.let {category ->
                category.totalInStock += product.stock

                category.products.add(
                    Product(
                        name = product.title,
                        imageUrl = product.thumbnail,
                        price = product.price,
                        stockQuantity = product.stock
                    )
                )

                // Since we are using data class, we need to set back the modified data object into the map.
                categories[product.category] = category
            }
        }

        return categories.values.sortedBy { it.name }.toList()
    }
}