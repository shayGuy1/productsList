package com.example.projectforiterview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectforiterview.R
import com.example.projectforiterview.viewmodel.FragmentsViewModel

class DetailFragment : Fragment() {

    private lateinit var fragmentsViewModel: FragmentsViewModel

    companion object {
        private const val DETAIL_FRAGMENT_ARG_ITEM = "detail_id"

        fun newInstance(id: Long): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putLong(DETAIL_FRAGMENT_ARG_ITEM, id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        fragmentsViewModel = ViewModelProvider(this)[FragmentsViewModel::class.java]

        val titleTextView = view.findViewById<TextView>(R.id.detailTitleTextView)

        val id = arguments?.getLong(DETAIL_FRAGMENT_ARG_ITEM)
        id?.let {
//            fragmentsViewModel.requestDetailsData(id)
//            fragmentsViewModel.dataItemLiveData.observe(viewLifecycleOwner) {
//                titleTextView.text =
//                    "${it.terminalName}\n${it.approvalNumber}\n${it.amountPaid}\n${it.cardType}\n${it.currencyCode}\n${it.entryNumber}\n${it.price}\n..."
//            }
        }

        return view
    }
}
