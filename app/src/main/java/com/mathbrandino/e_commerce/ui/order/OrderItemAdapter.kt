package com.mathbrandino.e_commerce.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mathbrandino.e_commerce.data.local.item.OrdemItemAndProduct
import com.mathbrandino.e_commerce.databinding.ItemOrderProductBinding
import com.mathbrandino.e_commerce.ui.order.OrderItemAdapter.OrderItemViewHolder

class OrderItemAdapter(private val items: List<OrdemItemAndProduct>) :
    RecyclerView.Adapter<OrderItemViewHolder>() {

    inner class OrderItemViewHolder(private val binding: ItemOrderProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OrdemItemAndProduct) {
            binding.itemOrderProductName.text = item.product.name
            binding.itemOrderProductQuantity.text = item.orderItem.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OrderItemViewHolder(ItemOrderProductBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

}
