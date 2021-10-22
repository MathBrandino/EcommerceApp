package com.mathbrandino.e_commerce.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mathbrandino.e_commerce.R
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

        fun bind(order: OrderWithItems) = with(binding) {
            itemOrderTitle.text =
                itemView.context.getString(R.string.order_item_title, order.order.id)
            itemOrderTotalText.text =
                itemView.context.getString(R.string.product_value, order.order.total)
            itemOrderProductList.adapter = OrderItemAdapter(order.items)
        }
    }
}