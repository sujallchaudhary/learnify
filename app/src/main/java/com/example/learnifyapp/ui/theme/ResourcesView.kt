package com.example.learnifyapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Resources() {
    var flashcardName by remember { mutableStateOf("") }
    var flashcardDescription by remember { mutableStateOf("") }
    val flashcards = remember { mutableStateListOf<Flashcard>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Input fields for creating a new flashcard
        TextField(
            value = flashcardName,
            onValueChange = { flashcardName = it },
            label = { Text("Flashcard Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = flashcardDescription,
            onValueChange = { flashcardDescription = it },
            label = { Text("Flashcard Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (flashcardName.isNotBlank() && flashcardDescription.isNotBlank()) {
                    flashcards.add(Flashcard(flashcardName, flashcardDescription))
                    flashcardName = ""
                    flashcardDescription = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Create Flashcard")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Display list of flashcards
        LazyColumn {
            items(flashcards) { flashcard ->
                FlashcardItem(flashcard = flashcard)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun FlashcardItem(flashcard: Flashcard) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = flashcard.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = flashcard.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

data class Flashcard(val name: String, val description: String)

@Preview(showBackground = true)
@Composable
fun PreviewFlashcardScreen() {
    Resources()
}