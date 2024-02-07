package com.example.mystore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.R
import com.example.mystore.activity.MainActivity
import com.example.mystore.adapter.CategoriesAdapter
import com.example.mystore.dialog.DialogHelper
import com.example.mystore.viewmodel.CategoryFragmentViewModel

class CategoriesFragment : Fragment() {

    private lateinit var categoryFragmentViewModel : CategoryFragmentViewModel
    private lateinit var loader : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val imageSize = resources.getDimensionPixelSize(R.dimen.image_category_size)

        loader = view.findViewById(R.id.progressBar)
        categoryFragmentViewModel = ViewModelProvider(this)[CategoryFragmentViewModel::class.java]
        categoryFragmentViewModel.listDataItemLiveData.observe(viewLifecycleOwner) {
            val adapter = CategoriesAdapter(it, imageSize) { item ->
                (activity as? MainActivity)?.showDetailFragment(item)
            }
            recyclerView.adapter = adapter
        }
        categoryFragmentViewModel.loadingErrorMessageLiveData.observe(viewLifecycleOwner) {message ->
            showErrorDialog(title = "Loading error", message = message)
        }
        categoryFragmentViewModel.loadingStatusLiveData.observe(viewLifecycleOwner) {loading ->
            setLoaderVisibility(loading)
        }
        categoryFragmentViewModel.requestListData()
        return view
    }

    private fun setLoaderVisibility(isVisible : Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog(title: String, message: String) {
        context?.let {
            DialogHelper.showErrorDialog(it, title, message) { resultCode ->
                if (resultCode == DialogHelper.ResultCode.Retry) {
                    categoryFragmentViewModel.requestListData()
                } else {
                    activity?.finish()
                }
            }
        }
    }
}
