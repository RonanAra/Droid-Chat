package br.com.droidchat.utils.extensions

import androidx.navigation.NavController

fun NavController.navigateWithPopUp(
    route: Any,
    popUpTo: Any = route,
    isInclusive: Boolean = true,
    launchSingleTop: Boolean = true,
) {
    this.navigate(route) {
        popUpTo(popUpTo) {
            inclusive = isInclusive
        }
        this.launchSingleTop = launchSingleTop
    }
}