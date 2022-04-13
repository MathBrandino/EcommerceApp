package com.mathbrandino.e_commerce.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mathbrandino.e_commerce.R

@Composable
fun TopBar(
    onBackPressed: () -> Unit,
    title: String,
    actions: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Filled.ArrowBack, "back button")
            }
        },
        actions = { actions?.let { it() } }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(onBackPressed = { }, title = stringResource(id = R.string.main_activity_title))
}