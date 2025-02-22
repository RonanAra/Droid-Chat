package br.com.droidchat.ui.feature.signin

data class SignInFormState(
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val isLoading: Boolean = false,
)