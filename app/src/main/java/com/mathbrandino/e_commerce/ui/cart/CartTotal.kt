package com.mathbrandino.e_commerce.ui.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathbrandino.e_commerce.R
import java.math.BigDecimal

@Composable
fun CartTotal(total: BigDecimal) {
    Text(
        text = stringResource(R.string.cart_total_text, total),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(BorderStroke(1.dp, color = Color.Black))
            .padding(16.dp),
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
    )
}

@Preview(showBackground = true)
@Composable
fun CartTotalPreview(){
    CartTotal(total = BigDecimal.TEN)
}