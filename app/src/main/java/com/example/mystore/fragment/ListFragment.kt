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
import com.example.mystore.adapter.ListAdapter
import com.example.mystore.viewmodel.FragmentsViewModel

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentsViewModel : FragmentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        fragmentsViewModel = ViewModelProvider(this)[FragmentsViewModel::class.java]

        fragmentsViewModel.listDataItemLiveData.observe(viewLifecycleOwner){
            val adapter = ListAdapter(it) { item ->
                (activity as? MainActivity)?.showDetailFragment(item)
            }
            recyclerView.adapter = adapter
        }
        fragmentsViewModel.requestListData()
        return view
    }

}
