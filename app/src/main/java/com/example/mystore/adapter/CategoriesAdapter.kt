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
import com.example.mystore.data.Category
import com.example.mystore.data.Product

class CategoriesAdapter(private val items: List<Category>, private  val imageSize : Int , private val onItemClick: (List<Product>) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryCard  : View = itemView.findViewById(R.id.categoryCard)
        private val textViewCategoryName: TextView = itemView.findViewById(R.id.textViewCategoryName)
        private val textViewTotalProducts: TextView = itemView.findViewById(R.id.textViewTotalProducts)
        private val textViewTotalStock: TextView = itemView.findViewById(R.id.textViewTotalStock)
        private val imageViewThumbnail : ImageView = itemView.findViewById(R.id.imageViewThumbnail)
        fun bind(data: Category) {
            textViewCategoryName.text = data.name
            textViewTotalProducts.text =
                itemView.context.getString(R.string.total_products, data.getDistinctCount().toString())
            textViewTotalStock.text = itemView.context.getString(R.string.total_stock, data.totalInStock.toString())
            Glide.with(textViewTotalProducts.context)
                .load(data.imageUrl)
                .apply(RequestOptions().override(imageSize, imageSize))
                .into(imageViewThumbnail)
            categoryCard.setOnClickListener { onItemClick(data.products) }
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
