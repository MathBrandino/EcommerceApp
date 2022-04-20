package com.mathbrandino.e_commerce.ui.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputText(
    initialValue: String,
    labelText: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    onValueChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    OutlinedTextField(
        value = initialValue,
        onValueChange = onValueChange,
        isError = isError,
        label = { Text(text = labelText) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = keyboardActions
    )
}

@Preview
@Composable
fun InputTextPreview() {
    InputText(initialValue = "", labelText = "Hint")
}

@Preview
@Composable
fun InputTextWithErrorPreview() {
    InputText(
        initialValue = "",
        labelText = "Hint",
        isError = true
    )
}