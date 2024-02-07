package com.example.mystore.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mystore.R
import com.example.mystore.fragment.DetailFragment
import com.example.mystore.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment())
                .commit()

    }

    fun showDetailFragment(id: Long) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // Specify the animations
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in,  // Enter from left
            R.anim.fade_out, // Exit to right
            R.anim.fade_in,  // Enter from right
            R.anim.slide_out // Pop exit animation
        )

        // Replace or add your fragments
        fragmentTransaction.replace(R.id.container, DetailFragment.newInstance(id))
        fragmentTransaction.addToBackStack(DetailFragment::class.simpleName)

        // Commit the transaction
        fragmentTransaction.commit()
    }
}

