package com.example.mystore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mystore.R
import com.example.mystore.data.Product

class ProductsAdapter(private val items: List<Product>, val imageSize: Int) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewProductsName: TextView = itemView.findViewById(R.id.textViewProductsName)
        private val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        private val textViewAvailableStock: TextView = itemView.findViewById(R.id.textViewAvailableStock)
        private val imageViewProducts : ImageView = itemView.findViewById(R.id.imageViewProducts)
        fun bind(data: Product) {
            textViewProductsName.text = data.name
            textViewPrice.text =
                itemView.context.getString(R.string.currency, data.price.toString())
            textViewAvailableStock.text =
                itemView.context.getString(R.string.available_in_stock, data.stockQuantity.toString())
            Glide.with(imageViewProducts.context)
                .load(data.imageUrl)
                .apply(RequestOptions().override(imageSize, imageSize))
                .into(imageViewProducts)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.products_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
