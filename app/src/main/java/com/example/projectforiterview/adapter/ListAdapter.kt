package com.example.projectforiterview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectforiterview.R
import com.example.projectforiterview.retrofit.data.BillingEntryHeader

class ListAdapter(private val items: List<BillingEntryHeader>, private val onItemClick: (Long) -> Unit) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textMiddle: TextView = itemView.findViewById(R.id.textMiddle)

        fun bind(data: BillingEntryHeader) {
            textMiddle.text = "${data.currencyCode} ${data.created}"

            itemView.setOnClickListener { onItemClick(data.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
