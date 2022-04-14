package com.mathbrandino.e_commerce.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.ui.common.TopBar
import com.mathbrandino.e_commerce.ui.form.ProductFormActivity
import com.mathbrandino.e_commerce.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : ComponentActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val product by lazy {
        intent!!.getParcelableExtra<Product>(PRODUCT_EXTRA)!!
    }

    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Top()
                        Content()
                        Bottom(Modifier.weight(1F, fill = false))
                    }

                }
            }
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    finish()
                }
            }
    }

    @Composable
    private fun Bottom(modifier: Modifier) {
        Button(
            onClick = { addIntoCart() },
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Blue200)

        ) {
            Text(
                text = stringResource(id = R.string.product_detail_button_text),
                color = White
            )
        }
    }

    @Composable
    private fun Content() {
        Column {
            Text(
                text = stringResource(id = R.string.product_detail_description_title),
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = product.description ?: "",
                fontSize = 16.sp,
                modifier = Modifier.padding(24.dp),
                color = Black100
            )
            Text(
                text = stringResource(id = R.string.product_detail_value_title),
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = stringResource(id = R.string.product_value, product.value),
                fontSize = 32.sp,
                modifier = Modifier.padding(24.dp),
                color = DarkBlue700
            )
        }
    }

    @Composable
    private fun Top() {
        Column {
            TopBar(onBackPressed = { finish() }, title = product.name, actions = {
                TextButton(onClick = { goToForm() }) {
                    Text(text = "Alterar", color = White)
                }
            })
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "Foto do produto: ${product.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }

    private fun addIntoCart() {
        viewModel.addIntoCart(product)
        Toast.makeText(this, R.string.product_detail_add_cart_success, Toast.LENGTH_SHORT)
            .show()
    }

    private fun goToForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        intent.putExtra(ProductFormActivity.PRODUCT_KEY, product)
        resultLauncher.launch(intent)
    }

    companion object {
        const val PRODUCT_EXTRA = "product"
    }
}