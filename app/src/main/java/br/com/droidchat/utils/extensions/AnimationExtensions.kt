package br.com.droidchat.utils.extensions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.slidInTo(
    direction: AnimatedContentTransitionScope.SlideDirection,
): EnterTransition {
    return slideIntoContainer(
        animationSpec = tween(300),
        towards = direction
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slidOutTo(
    direction: AnimatedContentTransitionScope.SlideDirection,
): ExitTransition {
    return slideOutOfContainer(
        animationSpec = tween(300),
        towards = direction
    )
}