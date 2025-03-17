package com.example.learnifyapp.DrawerTheme

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnifyapp.loginregister.clearLoginCredentials

class LogoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Logout(navController)
        }
    }
}

@Composable
fun Logout(navController: NavController) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                navController.popBackStack()
            },
            title = { Text("Logout") },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                Button(
                    onClick = {
                        // Clear login credentials
                        clearLoginCredentials(context)

                        // Navigate to the login screen
                        navController.navigate("login") {
                            popUpTo("main") { inclusive = true } // Clear the back stack
                        }
                    }
                ) {
                    Text("Logout")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        navController.popBackStack()
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}