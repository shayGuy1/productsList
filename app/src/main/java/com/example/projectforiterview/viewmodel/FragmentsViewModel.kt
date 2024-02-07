package com.example.projectforiterview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectforiterview.data.Category
import com.example.projectforiterview.retrofit.ProductsService
import com.example.projectforiterview.retrofit.data.Product
import com.example.projectforiterview.retrofit.data.ProductsResponse
import com.example.projectforiterview.retrofit.maneger.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class FragmentsViewModel : ViewModel() {

    private val listDataItemMutableLiveData = MutableLiveData<List<Category>>()


    val listDataItemLiveData: LiveData<List<Category>>
        get() = listDataItemMutableLiveData


    fun requestListData() {
        viewModelScope.launch(Dispatchers.IO) {
            requestListDatFromNetwork()
        }
    }

    private fun requestListDatFromNetwork() {
        val billingService = RetrofitManager.getInstance().create(ProductsService::class.java)
        val call = billingService.getProducts(100)
        call.enqueue(object : retrofit2.Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        val products = it.products
                        val categories = createCategories(products)
                        listDataItemMutableLiveData.postValue(categories)
                    } ?: {
                        // Handle empty body error
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun createCategories(products: List<Product>): List<Category> {
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
                category.totalDistinct += product.stock

                category.products.add(
                    com.example.projectforiterview.data.Product(
                        name = product.title,
                        imageUrl = product.thumbnail,
                        price = product.price,
                        stockQuantity = product.stock
                    )
                )

                categories[product.category] = category
            }
        }

        return categories.values.sortedBy { it.name }.toList()
    }
}