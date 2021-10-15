package com.mathbrandino.e_commerce.ui.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.domain.useCases.SaveProdutcUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductFormViewModel @Inject constructor(private val saveUseCase: SaveProdutcUseCase) : ViewModel() {

    fun save(product: Product) {
        viewModelScope.launch {
            saveUseCase.save(product)
        }
    }
}