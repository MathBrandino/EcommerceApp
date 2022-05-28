package com.mathbrandino.e_commerce.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mathbrandino.e_commerce.data.local.product.Product

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductView(item: Product, onClick: (Product) -> Unit) {
    Card(
        onClick = { onClick(item) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = item.name,
                modifier = Modifier
                    .padding(24.dp)
                    .align(Alignment.BottomStart),
                style = TextStyle(
                    color = Color.White,
                    background = Color(android.graphics.Color.parseColor("#80000000")),
                ),
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
            )
        }
    }

}
