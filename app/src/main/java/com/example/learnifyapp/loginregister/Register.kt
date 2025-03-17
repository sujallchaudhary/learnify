package com.example.learnifyapp.loginregister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learnifyapp.model.AuthViewModel

@Composable
fun RegisterScreen(onRegisterSuccess: (String) -> Unit, onNavigateToLogin: () -> Unit) {
    val viewModel: AuthViewModel = viewModel()
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val grade = remember { mutableStateOf("") }
    val instituteName = remember { mutableStateOf("") }
    val error = viewModel.error
    val registerResponse = viewModel.registerResponse

    LaunchedEffect(registerResponse) {
        registerResponse?.let { response ->
            onRegisterSuccess(response.token) // Navigate to the next screen on successful registration
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Register", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { newValue -> name.value = newValue },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

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
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = grade.value,
            onValueChange = { newValue -> grade.value = newValue },
            label = { Text("Grade") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = instituteName.value,
            onValueChange = { newValue -> instituteName.value = newValue },
            label = { Text("Institute Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.register(name.value, email.value, password.value, grade.value, instituteName.value)
        }) {
            Text("Register")
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onNavigateToLogin) {
            Text("Already have an account? Login")
        }

        if (error != null) {
            Text(text = error, color = Color.Red)
        }
    }
}