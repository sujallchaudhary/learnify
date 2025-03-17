package com.example.learnifyapp.DrawerTheme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Terms() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Terms & Conditions", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("By using Learnify, you agree to abide by the following terms and conditions.")

        SectionTitle("1. Use of Services")
        SectionContent("Learnify is designed to assist students in learning through AI-generated roadmaps and resources.")

        SectionTitle("2. Account Responsibilities")
        SectionContent("You are responsible for maintaining the confidentiality of your account credentials.")

        SectionTitle("3. AI-Generated Content")
        SectionContent("While we strive for accuracy, AI-generated content may not always be 100% correct. Users should verify information when necessary.")

        SectionTitle("4. Prohibited Activities")
        SectionContent("You may not misuse Learnify for any unauthorized or illegal activities.")

        SectionTitle("5. Modifications")
        SectionContent("We reserve the right to modify these terms at any time. Continued use of the app implies acceptance of the updated terms.")

        SectionTitle("6. Contact Us")
        SectionContent("If you have any questions regarding our Terms & Conditions, reach out to support@learnify.com.")
    }
}

@Preview
@Composable
fun PreviewTermsConditionsScreen() {
    Terms()
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
    )
}

@Composable
fun SectionContent(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.bodyLarge
    )
}
