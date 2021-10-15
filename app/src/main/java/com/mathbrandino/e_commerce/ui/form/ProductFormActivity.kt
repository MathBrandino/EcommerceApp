package com.mathbrandino.e_commerce.ui.form

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.size.Scale
import com.google.android.material.textfield.TextInputLayout
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.databinding.ActivityProductFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFormActivity : AppCompatActivity() {

    private var id = 0

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private val product by lazy {
        intent?.getParcelableExtra<Product>(PRODUCT_KEY)
    }

    private val viewModel: ProductFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.productFormImageUrl.editText?.apply {
            setOnEditorActionListener { _, _, _ ->
                searchImage(text.toString())
                true
            }
        }
        binding.productFormImageSearch.setOnClickListener {
            searchImage(binding.productFormImageUrl.editText?.text.toString())
        }

        product?.let {
            id = it.id
            binding.productFormName.editText?.setText(it.name)
            binding.productFormImageUrl.editText?.setText(it.imageUrl)
            binding.productFormValue.editText?.setText(it.value.toString())
            binding.productFormDescription.editText?.setText(it.description)
            searchImage(it.imageUrl)
        }
    }

    private fun searchImage(link: String) {
        binding.productFormImage.load(link) {
            scale(Scale.FIT)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_form_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        R.id.productFormSave -> {
            if (formIsValid()) {
                viewModel.save(getProductFromForm())
                setResult(RESULT_OK)
                finish()
            }
            true
        }
        else -> true
    }

    private fun getProductFromForm(): Product {
        fun extractStringFrom(til: TextInputLayout): String {
            return til.editText?.text.toString()
        }

        val name = extractStringFrom(binding.productFormName)
        val description = extractStringFrom(binding.productFormDescription)
        val urlImage = extractStringFrom(binding.productFormImageUrl)
        val value = extractStringFrom(binding.productFormValue).toDouble()

        return Product(
            name = name,
            description = description,
            imageUrl = urlImage,
            value = value,
            id = id
        )
    }

    private fun formIsValid(): Boolean {
        fun validateTextInputLayout(til: TextInputLayout, @StringRes errorMessage: Int): Boolean {
            return til.editText?.text.isNullOrBlank().not()
                .also { if (!it) til.error = getString(errorMessage) else til.error = null }
        }

        val urlIsValid =
            validateTextInputLayout(
                binding.productFormImageUrl,
                R.string.product_form_menu_url_error
            )
        val nameIsValid =
            validateTextInputLayout(
                binding.productFormName,
                R.string.product_form_menu_name_error
            )
        val valueIsValid =
            validateTextInputLayout(
                binding.productFormValue,
                R.string.product_form_value_hint
            )

        return urlIsValid && nameIsValid && valueIsValid
    }


    companion object {
        const val PRODUCT_KEY = "product"
    }
}