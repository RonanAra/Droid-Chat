package br.com.droidchat.ui.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.droidchat.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _formEvent: Channel<SignInFormState> = Channel()
    val formEvent: Channel<SignInFormState> = _formEvent

    private val _formState = MutableStateFlow(SignInFormState())
    val formState: StateFlow<SignInFormState> = _formState.asStateFlow()

    fun onFormEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> emailChanged(event.email)
            is SignInFormEvent.PasswordChanged -> passwordChanged(event.password)
            SignInFormEvent.Submit -> doSignIn()
        }
    }

    private fun emailChanged(email: String) {
        _formState.update { state ->
            state.copy(
                email = email,
                emailError = null
            )
        }
    }

    private fun passwordChanged(password: String) {
        _formState.update { state ->
            state.copy(
                password = password,
                passwordError = null
            )
        }
    }

    private fun doSignIn() {
        var isFormValid = true
        resetFormErrorState()
        if (_formState.value.email.isBlank()) {
            _formState.update { it.copy(emailError = R.string.error_message_email_invalid) }
            isFormValid = false
        }

        if (formState.value.password.isBlank()) {
            _formState.update { it.copy(passwordError = R.string.error_message_password_invalid) }
            isFormValid = false
        }

        if (isFormValid) {
            resetFormErrorState()
            viewModelScope.launch {
                _formState.update { it.copy(isLoading = true) }
                delay(3000L)
                _formState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun resetFormErrorState() {
        _formState.update { state ->
            state.copy(
                emailError = null,
                passwordError = null
            )
        }
    }
}