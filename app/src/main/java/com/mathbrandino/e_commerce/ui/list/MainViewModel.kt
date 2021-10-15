package com.mathbrandino.e_commerce.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mathbrandino.e_commerce.domain.useCases.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    searchProductUseCase: SearchProductUseCase
) : ViewModel() {

    val products = searchProductUseCase.search().asLiveData()

}