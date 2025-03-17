package com.example.learnifyapp.DrawerTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnifyapp.ui.theme.AppBackground

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground) // Light gray background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Profile Page", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))
        // Box for Name
        ProfileBox(label = "Name", value = "Anmol")
        Spacer(modifier = Modifier.height(16.dp))

        // Box for Email
        ProfileBox(label = "Email", value = "anmolgarg@gmail.com")
        Spacer(modifier = Modifier.height(16.dp))

        // Box for Grade
        ProfileBox(label = "Grade", value = "12")
        Spacer(modifier = Modifier.height(16.dp))

        // Box for Institute
        ProfileBox(label = "Institute", value = "IIT")
    }
}

@Composable
fun ProfileBox(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp)) // White box with rounded corners
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PreviewProfilePage() {
    Profile()
}