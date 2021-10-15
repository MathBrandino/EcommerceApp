package com.mathbrandino.e_commerce.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.databinding.ItemProductBinding

class ProductAdapter(
    private val products: List<Product>,
    private val onClick: (Product, View) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(products[position])

    override fun getItemCount(): Int = products.size


    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.itemProductImage.load(product.imageUrl)
            binding.itemProductName.text = product.name
            binding.root.setOnClickListener { onClick(product, binding.root) }
        }
    }
}