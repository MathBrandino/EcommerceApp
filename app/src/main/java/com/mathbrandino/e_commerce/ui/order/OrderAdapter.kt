package com.mathbrandino.e_commerce.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mathbrandino.e_commerce.data.local.order.OrderWithItems
import com.mathbrandino.e_commerce.databinding.ItemOrderBinding

class OrderAdapter(private val orders: List<OrderWithItems>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return OrderViewHolder(ItemOrderBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) =
        holder.bind(orders[position])

    override fun getItemCount() = orders.size

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: OrderWithItems) {
            binding.itemOrderTitle.text = "Pedido #${order.order.id}"
            binding.itemOrderTotalText.text = "R$ ${order.order.total}"
            binding.itemOrderProductList.adapter = OrderItemAdapter(order.items)
        }
    }
}