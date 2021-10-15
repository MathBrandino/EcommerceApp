package com.mathbrandino.e_commerce.ui.detail

import androidx.lifecycle.ViewModel
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.domain.useCases.AddProductCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val useCase: AddProductCartUseCase) :
    ViewModel() {


    fun addIntoCart(product: Product) {
        useCase.add(product)
    }
}