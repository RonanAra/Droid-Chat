package br.com.droidchat.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.droidchat.R
import br.com.droidchat.ui.theme.ColorSuccess
import br.com.droidchat.ui.theme.DroidChatTheme
import br.com.droidchat.utils.extensions.bottomBorder
import br.com.droidchat.utils.extensions.passwordTransformation

@Composable
fun SecondaryTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    extraText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = { text ->
            onValueChange(text)
        },
        modifier = modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = if (keyboardType == KeyboardType.Text) KeyboardCapitalization.Sentences
            else KeyboardCapitalization.None,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        singleLine = true,
        maxLines = 1,
        visualTransformation = keyboardType.passwordTransformation(passwordVisible)
    ) { innerTextField ->
        SecondaryTextFieldContent(
            label = label,
            extraText = extraText,
            value = value,
            onChangeVisibilityIcon = {
                passwordVisible = !passwordVisible
            },
            keyboardType = keyboardType,
            passwordVisible = passwordVisible,
            innerTextField = innerTextField
        )
    }
}

@Composable
private fun SecondaryTextFieldContent(
    label: String,
    value: String,
    extraText: String?,
    passwordVisible: Boolean,
    keyboardType: KeyboardType,
    onChangeVisibilityIcon: () -> Unit,
    innerTextField: @Composable () -> Unit,
) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    modifier = Modifier .bottomBorder(
                        color = Color.Black,
                        strokeWidth = 2.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                    Spacer(Modifier.weight(1f))

                    extraText?.let { text ->
                        Text(
                            text = text,
                            modifier = Modifier.padding(4.dp),
                            color = ColorSuccess,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (keyboardType == KeyboardType.Password && value.isNotEmpty()) {
                        val visibilityIcon = if (passwordVisible) R.drawable.ic_visibility
                        else R.drawable.ic_visibility_off

                        IconButton(onClick = onChangeVisibilityIcon) {
                            Icon(
                                painter = painterResource(visibilityIcon),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DroidChatTheme {
        SecondaryTextField(
            label = "E-mail",
            value = "",
            onValueChange = {},
            extraText = "as senhas são iguais",
            keyboardType = KeyboardType.Password
        )
    }
}