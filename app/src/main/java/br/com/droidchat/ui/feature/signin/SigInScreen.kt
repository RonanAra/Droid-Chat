package br.com.droidchat.ui.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.droidchat.R
import br.com.droidchat.ui.components.PrimaryButton
import br.com.droidchat.ui.components.PrimaryTextField
import br.com.droidchat.ui.theme.BackgroundGradient
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = viewModel(),
    navigateToSignUp: () -> Unit
) {
    val formState by viewModel.formState.collectAsState()
    SignInScreen(
        onFormEvent = viewModel::onFormEvent,
        formState = formState,
        onRegisterClick = navigateToSignUp
    )
}

@Composable
fun SignInScreen(
    formState: SignInFormState,
    onFormEvent: (SignInFormEvent) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null
        )

        Spacer(Modifier.height(78.dp))

        PrimaryTextField(
            modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen.spacing_medium)
            ),
            value = formState.email,
            onValueChange = { email ->
                onFormEvent(SignInFormEvent.EmailChanged(email))
            },
            errorMessage = formState.emailError?.let { stringResource(it) },
            keyboardType = KeyboardType.Email,
            placeHolder = stringResource(R.string.feature_login_email),
            leadingIconRes = R.drawable.ic_envelope
        )

        Spacer(Modifier.height(14.dp))

        PrimaryTextField(
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_medium)),
            value = formState.password,
            onValueChange = { password ->
                onFormEvent(SignInFormEvent.PasswordChanged(password))
            },
            keyboardType = KeyboardType.Password,
            placeHolder = stringResource(R.string.feature_login_password),
            leadingIconRes = R.drawable.ic_lock,
            errorMessage = formState.passwordError?.let { stringResource(it) }
        )

        Spacer(Modifier.height(98.dp))

        PrimaryButton(
            text = stringResource(R.string.feature_login_button),
            modifier = Modifier.padding(
                dimensionResource(R.dimen.spacing_medium)
            ),
            onClick = { onFormEvent(SignInFormEvent.Submit) },
            isLoading = formState.isLoading
        )

        Spacer(Modifier.height(56.dp))

        val noAccountText = stringResource(R.string.feature_login_no_account)
        val registerText = stringResource(R.string.feature_login_register)
        val noAccountRegisterText = "$noAccountText $registerText"
        val annotatedString = buildAnnotatedString {
            val registerTextStartIndex = noAccountRegisterText.indexOf(registerText)
            val registerTextEndIndex = registerTextStartIndex + registerText.length

            append(noAccountRegisterText)

            addStyle(
                style = SpanStyle(
                    color = Color.White,
                ),
                start = 0,
                end = registerTextEndIndex
            )

            addLink(
                clickable = LinkAnnotation.Clickable(
                    tag = "register_text",
                    styles = TextLinkStyles(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline
                        )
                    ),
                    linkInteractionListener = { onRegisterClick() },
                ),
                start = registerTextStartIndex,
                end = registerTextEndIndex
            )
        }

        Text(annotatedString)
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        SignInScreen(
            formState = SignInFormState(),
            onFormEvent = {},
            onRegisterClick = {}
        )
    }
}