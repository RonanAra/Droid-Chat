package br.com.droidchat.utils.extensions

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

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

inline fun <reified T : Any> NavGraphBuilder.commonNavComposable(
    noinline content: @Composable T.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        enterTransition = { this.baseInSlideIntoContainer() },
        exitTransition = { this.baseOutSlideIntoContainer() }
    ) {  navBackStackEntry ->
        navBackStackEntry.toRoute<T>().content(navBackStackEntry)
    }
}