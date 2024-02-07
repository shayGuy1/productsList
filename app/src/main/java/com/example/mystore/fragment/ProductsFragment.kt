package com.example.mystore.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.R
import com.example.mystore.adapter.ProductsAdapter
import com.example.mystore.data.Product

class ProductsFragment : Fragment() {

    companion object {
        private const val PRODUCTS_FRAGMENT_ARG_ITEM = "PRODUCTS_FRAGMENT_ARG_ITEM"

        fun newInstance(products: List<Product>): ProductsFragment {
            val fragment = ProductsFragment()
            val args = Bundle()
            args.putParcelableArrayList(PRODUCTS_FRAGMENT_ARG_ITEM, products as ArrayList<Product>)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val imageSize = resources.getDimensionPixelSize(R.dimen.image_product_size)


        val products = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelableArrayList(PRODUCTS_FRAGMENT_ARG_ITEM, Product::class.java)
        } else {
            arguments?.getParcelableArrayList(PRODUCTS_FRAGMENT_ARG_ITEM)
        }
        products?.let {
            recyclerView.adapter = ProductsAdapter(it,imageSize)
        }
        return view
    }
}
