package com.mathbrandino.e_commerce.ui.form

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.ui.common.TopBar
import com.mathbrandino.e_commerce.ui.theme.EcommerceTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalComposeUiApi::class)
@AndroidEntryPoint
class ProductFormActivity : ComponentActivity() {

    private val product by lazy {
        intent?.getParcelableExtra<Product>(PRODUCT_KEY)
    }

    private val viewModel: ProductFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var imgLink by mutableStateOf(product?.imageUrl ?: "")
        var name by mutableStateOf(product?.name ?: "")
        var description by mutableStateOf(product?.description ?: "")
        var amount by mutableStateOf(product?.value?.toString() ?: "")
        var imgLinkValid by mutableStateOf(false)
        var nameValid by mutableStateOf(false)
        var amountValid by mutableStateOf(false)

        setContent {
            EcommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val keyboardController = LocalSoftwareKeyboardController.current
                    ProvideWindowInsets {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .navigationBarsWithImePadding(),
                        ) {
                            TopBar(
                                onBackPressed = { finish() },
                                title = stringResource(id = R.string.product_form_title),
                                actions = {
                                    IconButton(onClick = {
                                        fun isValid(): Boolean {
                                            fun validate(text: String) =
                                                text.isNotBlank()
                                            return validate(imgLink).also {
                                                imgLinkValid = !it
                                            } && validate(name).also {
                                                nameValid = !it
                                            } && validate(amount).also {
                                                amountValid = !it
                                            }
                                        }
                                        if (isValid()) save(name, description, amount, imgLink)
                                    }) {
                                        Icon(
                                            Icons.Default.Done,
                                            contentDescription = stringResource(
                                                id = R.string.product_form_menu_save_text
                                            )
                                        )
                                    }
                                }
                            )
                            AsyncImage(
                                model = imgLink.ifBlank { },
                                contentDescription = stringResource(id = R.string.product_form_image_description),
                                error = painterResource(id = R.drawable.ic_no_image),
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                            InputText(
                                initialValue = imgLink,
                                labelText = stringResource(id = R.string.product_form_image_hint),
                                onValueChange = { imgLink = it },
                                modifier = Modifier.padding(16.dp),
                                isError = imgLinkValid
                            )
                            InputText(
                                initialValue = name,
                                labelText = stringResource(id = R.string.product_form_name_hint),
                                onValueChange = { name = it },
                                modifier = Modifier.padding(16.dp),
                                isError = nameValid

                            )
                            InputText(
                                initialValue = description,
                                labelText = stringResource(id = R.string.product_form_description_hint),
                                onValueChange = { description = it },
                                modifier = Modifier.padding(16.dp)
                            )
                            InputText(
                                initialValue = amount,
                                labelText = stringResource(id = R.string.product_form_value_hint),
                                onValueChange = { amount = it },
                                modifier = Modifier.padding(16.dp),
                                isError = amountValid,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                                keyboardActions = KeyboardActions(
                                    onDone = { keyboardController?.hide() }
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun save(
        name: String,
        description: String,
        amount: String,
        imgLink: String
    ) {
        viewModel.save(
            Product(
                name = name,
                description = description,
                value = amount.toDouble(),
                imageUrl = imgLink,
                id = product?.id ?: 0
            )
        )
        setResult(RESULT_OK)
        finish()
    }

    companion object {
        const val PRODUCT_KEY = "product"
    }
}