package br.com.droidchat.navigation

import kotlinx.serialization.Serializable

sealed interface ChatAppDestinations {
    @Serializable
    data object SplashRoute : ChatAppDestinations

    @Serializable
    data object SignInRoute : ChatAppDestinations

    @Serializable
    data object SignUpRoute : ChatAppDestinations
}