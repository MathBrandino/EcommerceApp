package com.mathbrandino.e_commerce.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.size.Scale
import com.google.android.material.textfield.TextInputLayout
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.databinding.ActivityProductFormBinding

class ProductFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

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
                Toast.makeText(this, "valido", Toast.LENGTH_LONG).show()
            }
            true
        }
        else -> true
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
}