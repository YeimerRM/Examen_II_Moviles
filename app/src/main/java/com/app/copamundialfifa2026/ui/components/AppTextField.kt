package com.app.copamundialfifa2026.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false
) {
    OutlinedTextField(
        value               = value,
        onValueChange       = onValueChange,
        modifier            = modifier.fillMaxWidth(),
        label               = { Text(label) },
        singleLine          = singleLine,
        enabled             = enabled,
        visualTransformation = visualTransformation,
        trailingIcon        = trailingIcon,
        leadingIcon         = leadingIcon,
        supportingText      = supportingText,
        isError             = isError
    )
}
