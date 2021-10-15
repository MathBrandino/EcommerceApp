package com.mathbrandino.e_commerce.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.databinding.ItemCartBinding
import com.mathbrandino.e_commerce.domain.model.CartItem

class CartAdapter(
    private val items: List<CartItem>,
    private val add: (CartItem) -> Unit,
    private val minus: (CartItem) -> Boolean
) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size


    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            binding.itemCartImage.load(cartItem.product.imageUrl)
            binding.itemCartName.text = cartItem.product.name
            binding.itemCartQuantity.text = cartItem.quantity.toString()
            binding.itemCartTotalPrice.text =
                itemView.context.getString(R.string.product_value, cartItem.getTotal().toDouble())

            binding.itemCartAdd.setOnClickListener {
                add(cartItem)
                notifyItemChanged(adapterPosition)
            }

            binding.itemCartMinus.setOnClickListener {
                val removed = minus(cartItem)
                if (removed) notifyItemRemoved(adapterPosition)
                else notifyItemChanged(adapterPosition)
            }
        }

    }
}
