package br.com.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.droidchat.navigation.ChatAppDestinations.SignInRoute
import br.com.droidchat.navigation.ChatAppDestinations.SignUpRoute
import br.com.droidchat.navigation.ChatAppDestinations.SplashRoute
import br.com.droidchat.ui.feature.signin.SignInRoute
import br.com.droidchat.ui.feature.splash.SplashRoute
import br.com.droidchat.utils.extensions.navigateWithPopUp

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashRoute {
                navController.navigateWithPopUp(
                    route = SignInRoute,
                    popUpTo = SplashRoute
                )
            }
        }

        composable<SignInRoute> {
            SignInRoute(
                navigateToSignUp = {
                    navController.navigate(SignUpRoute)
                }
            )
        }

        composable<SignUpRoute> {

        }
    }
}