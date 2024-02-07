package com.example.mystore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val imageSize = resources.getDimensionPixelSize(R.dimen.image_category_size)

        categoryFragmentViewModel = ViewModelProvider(this)[CategoryFragmentViewModel::class.java]
        categoryFragmentViewModel.listDataItemLiveData.observe(viewLifecycleOwner) {
            val adapter = CategoriesAdapter(it, imageSize) { item ->
                (activity as? MainActivity)?.showDetailFragment(item)
            }
            recyclerView.adapter = adapter
        }
        categoryFragmentViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            context?.let { context ->
                DialogHelper.showErrorDialog(context, it.first, it.second)
            }
        }
        categoryFragmentViewModel.requestListData()
        return view
    }

}
