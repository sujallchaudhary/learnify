package com.example.learnifyapp.loginregister

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnifyapp.ui.theme.MainView

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { token ->
                    // Navigate to the next screen (e.g., Profile or Dashboard)
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true } // Clear the back stack
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { token ->
                    // Navigate to the next screen (e.g., Profile or Dashboard)
                    navController.navigate("main") {
                        popUpTo("register") { inclusive = true } // Clear the back stack
                    }
                },
                onNavigateToLogin = {
                    navController.navigate("login")
                }
            )
        }
        composable("main") {
            MainView() // Your MainView composable
        }
    }
}