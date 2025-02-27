package br.com.droidchat.utils.extensions

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun KeyboardType.passwordTransformation(
    visibility: Boolean
): VisualTransformation {
    return if (this == KeyboardType.Password) {
        if (visibility) VisualTransformation.None
        else PasswordVisualTransformation()
    } else VisualTransformation.None
}