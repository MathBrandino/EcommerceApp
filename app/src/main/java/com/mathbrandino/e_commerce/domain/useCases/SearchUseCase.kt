package com.mathbrandino.e_commerce.domain.useCases

import com.mathbrandino.e_commerce.data.ProductRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: ProductRepository) {
     fun search() = repository.searchAll()
}