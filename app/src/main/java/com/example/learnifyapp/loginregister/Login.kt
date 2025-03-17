package com.example.learnifyapp.loginregister

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learnifyapp.model.AuthViewModel

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit, onNavigateToRegister: () -> Unit) {
    val viewModel: AuthViewModel = viewModel()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val error = viewModel.error
    val loginResponse = viewModel.loginResponse

    LaunchedEffect(loginResponse) {
        loginResponse?.let { response ->
            onLoginSuccess(response.token) // Navigate to the next screen on successful login
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { newValue -> email.value = newValue },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { newValue -> password.value = newValue },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.login(email.value, password.value) }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onNavigateToRegister) {
            Text("Don't have an account? Register")
        }

        if (error != null) {
            Text(text = error, color = Color.Red)
        }
    }
}