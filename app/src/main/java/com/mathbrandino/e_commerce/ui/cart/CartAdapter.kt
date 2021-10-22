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
            with(binding){
                itemCartImage.load(cartItem.product.imageUrl)
                itemCartName.text = cartItem.product.name
                itemCartQuantity.text = cartItem.quantity.toString()
                itemCartTotalPrice.text =
                    itemView.context.getString(R.string.product_value, cartItem.getTotal().toDouble())

                itemCartAdd.setOnClickListener {
                    add(cartItem)
                    notifyItemChanged(adapterPosition)
                }

                itemCartMinus.setOnClickListener {
                    val removed = minus(cartItem)
                    if (removed) notifyItemRemoved(adapterPosition)
                    else notifyItemChanged(adapterPosition)
                }
            }
        }

    }
}
