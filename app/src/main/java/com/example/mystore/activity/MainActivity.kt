package com.example.mystore.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mystore.R
import com.example.mystore.data.Product
import com.example.mystore.fragment.ProductsFragment
import com.example.mystore.fragment.CategoriesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CategoriesFragment())
                .commit()

    }

    fun showDetailFragment(products: List<Product>) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
        fragmentTransaction.add(R.id.container, ProductsFragment.newInstance(products))
        fragmentTransaction.addToBackStack(ProductsFragment::class.simpleName)
        fragmentTransaction.commit()
    }
}

