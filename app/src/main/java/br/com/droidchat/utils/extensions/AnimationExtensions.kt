package br.com.droidchat.utils.extensions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.baseInSlideIntoContainer(): EnterTransition {
    return slideIntoContainer(
        animationSpec = tween(300),
        towards = AnimatedContentTransitionScope.SlideDirection.Right
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.baseOutSlideIntoContainer(): ExitTransition {
    return  slideOutOfContainer(
        animationSpec = tween(300),
        towards = AnimatedContentTransitionScope.SlideDirection.Left
    )
}