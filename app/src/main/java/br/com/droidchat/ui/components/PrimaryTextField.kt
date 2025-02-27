package br.com.droidchat.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.droidchat.R
import br.com.droidchat.ui.theme.DroidChatTheme
import br.com.droidchat.utils.extensions.passwordTransformation

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes leadingIconRes: Int? = null,
    placeHolder: String = "",
    errorMessage: String? = null,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
) {
    val isKeyBoardTypePassword = keyboardType == KeyboardType.Password
    var passwordVisibility by remember { mutableStateOf(false) }

    val trailingIcon = if (passwordVisibility) R.drawable.ic_visibility
    else R.drawable.ic_visibility_off

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            trailingIcon = {
                if (isKeyBoardTypePassword && value.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable { passwordVisibility = !passwordVisibility },
                        painter = painterResource(trailingIcon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            leadingIcon = {
                leadingIconRes?.let { icon ->
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            singleLine = singleLine,
            visualTransformation = keyboardType.passwordTransformation(passwordVisibility),
            placeholder = { Text(text = placeHolder) },
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = if (errorMessage != null) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onSurfaceVariant,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            )
        )

        errorMessage?.let {
            Text(
                text = it,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        PrimaryTextField(
            leadingIconRes = R.drawable.ic_envelope,
            value = "123445",
            errorMessage = "Senha é obrigatória",
            keyboardType = KeyboardType.Password,
            placeHolder = "E-Mail",
            onValueChange = {}
        )
    }
}