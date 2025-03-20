package br.com.droidchat.ui.feature.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.droidchat.R
import br.com.droidchat.ui.components.PrimaryButton
import br.com.droidchat.ui.components.SecondaryTextField
import br.com.droidchat.ui.theme.BackgroundGradient
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignUpRoute() {
    SignUpScreen()
}

@Composable
fun SignUpScreen() {
    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.padding(
                    top = 56.dp,
                    bottom = 16.dp
                )
            )

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ),
                color = MaterialTheme.colorScheme.surface
            ) {
                SignUpContent()
            }
        }
    }
}

@Composable
fun SignUpContent() {
    Column(Modifier.padding(16.dp)) {

        SecondaryTextField(
            label = stringResource(R.string.feature_sign_up_first_name),
            value = "",
            onValueChange = {}
        )
        Spacer(Modifier.height(16.dp))

        SecondaryTextField(
            label = stringResource(R.string.feature_sign_up_last_name),
            value = "",
            onValueChange = {}
        )
        Spacer(Modifier.height(16.dp))

        SecondaryTextField(
            label = stringResource(R.string.feature_sign_up_email),
            value = "",
            onValueChange = {},
            keyboardType = KeyboardType.Email
        )

        Spacer(Modifier.height(16.dp))

        SecondaryTextField(
            label = stringResource(R.string.feature_sign_up_password),
            value = "",
            onValueChange = {},
            keyboardType = KeyboardType.Password
        )

        Spacer(Modifier.height(16.dp))

        SecondaryTextField(
            label = stringResource(R.string.feature_sign_up_password_confirmation),
            value = "",
            onValueChange = {},
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
        Spacer(Modifier.height(36.dp))

        PrimaryButton(
            text = stringResource(R.string.feature_sign_up_button),
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    DroidChatTheme {
        SignUpScreen()
    }
}
