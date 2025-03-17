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
fun PrivacyPolicy() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Privacy Policy", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Welcome to Learnify! Your privacy is important to us.")

        SectionTitle("1. Information We Collect")
        SectionContent("We collect user data such as name, email, study preferences, and activity logs to personalize your experience.")

        SectionTitle("2. How We Use Your Data")
        SectionContent("Your data is used for creating customized roadmaps, performance tracking, and doubt resolution.")

        SectionTitle("3. Data Protection")
        SectionContent("We implement security measures to protect your information from unauthorized access.")

        SectionTitle("4. Third-Party Services")
        SectionContent("We may use third-party AI tools for generating personalized learning materials.")

        SectionTitle("5. Contact Us")
        SectionContent("If you have any questions about this Privacy Policy, please contact us at support@learnify.com.")
    }
}

@Preview
@Composable
fun PreviewPrivacyPolicyScreen() {
    PrivacyPolicy()
}

