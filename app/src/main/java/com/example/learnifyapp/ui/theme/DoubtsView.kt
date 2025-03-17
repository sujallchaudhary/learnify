package com.example.learnifyapp.ui.theme

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun Doubts() {
    var doubtText by remember { mutableStateOf("") }
    var solutionText by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Doubt Input Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = doubtText,
                    onValueChange = { doubtText = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    decorationBox = { innerTextField ->
                        if (doubtText.isEmpty()) {
                            Text(
                                text = "Enter your doubt...",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            coroutineScope.launch {
                                solutionText = fetchSolution(doubtText, imageUri, context)
                            }
                        }
                        .padding(8.dp)
                )
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Upload Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            imagePickerLauncher.launch("image/*")
                        }
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Image Preview
        imageUri?.let { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentDescription = "Doubt Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Solution Box
        if (solutionText.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = solutionText,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}

suspend fun fetchSolution(doubtText: String, imageUri: Uri?, context: android.content.Context): String {
    // Implement the API call to fetch the solution using the provided routes
    // You can use Retrofit or another networking library to make the API call
    // For now, returning a dummy solution
    return "The correct answer is:\n" +
            "\n" +
            "a) int\n" +
            "\n" +
            "Explanation:\n" +
            "In most programming languages like C, C++, Java, Python, and Kotlin, the index of an array must be an integer (int) type because array indexing represents discrete memory locations."
}