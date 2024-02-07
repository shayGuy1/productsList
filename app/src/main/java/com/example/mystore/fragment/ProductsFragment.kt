package com.example.mystore.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mystore.R
import com.example.mystore.data.Category
import com.example.mystore.data.Product

class ProductsFragment : Fragment() {

    companion object {
        private const val PRODUCTS_FRAGMENT_ARG_ITEM = "PRODUCTS_FRAGMENT_ARG_ITEM"

        fun newInstance(category: Category): ProductsFragment {
            val fragment = ProductsFragment()
            val args = Bundle()
            args.putParcelable(PRODUCTS_FRAGMENT_ARG_ITEM, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.list_fragment, container, false)

        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(PRODUCTS_FRAGMENT_ARG_ITEM, Product::class.java)
        } else {
            arguments?.getParcelable(PRODUCTS_FRAGMENT_ARG_ITEM)
        }
        product?.let {

        }

        return view
    }
}
