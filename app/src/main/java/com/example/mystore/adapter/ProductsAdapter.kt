package com.example.mystore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystore.R
import com.example.mystore.data.Category
import com.example.mystore.data.Product

class ProductsAdapter(private val items: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryCard  : View = itemView.findViewById(R.id.categoryCard)
        private val textViewCategoryName: TextView = itemView.findViewById(R.id.textViewCategoryName)
        private val textViewTotalProducts: TextView = itemView.findViewById(R.id.textViewTotalProducts)
        private val textViewTotalStock: TextView = itemView.findViewById(R.id.textViewTotalStock)
        private val imageViewThumbnail : ImageView = itemView.findViewById(R.id.imageViewThumbnail)
        fun bind(data: Product) {
            textViewCategoryName.text = "Category: ${data.name}"
//            textViewTotalStock.text = "Total stock of: ${data.getTotalStock()}"
//            textViewTotalProducts.text = "Total products of: ${data.totalDistinct}"
            Glide.with(textViewTotalProducts.context)
                .load(data.imageUrl)
                .into(imageViewThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
