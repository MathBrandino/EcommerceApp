package com.mathbrandino.e_commerce.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mathbrandino.e_commerce.domain.useCases.SearchOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(useCase: SearchOrderUseCase) : ViewModel() {

    val orders = useCase.search()

}