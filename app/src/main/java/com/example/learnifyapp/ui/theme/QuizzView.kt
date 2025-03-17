package com.example.learnifyapp.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learnifyapp.model.QuizViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*


@Composable
fun Quizz() {
    val viewModel: QuizViewModel = viewModel()
    val triviaQuestions by viewModel.triviaQuestions.observeAsState()
    val filteredQuestions by viewModel.filteredQuestions.observeAsState()
    val selectedCategory by viewModel.selectedCategory.observeAsState()
    val error by viewModel.error.observeAsState()

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showAnswer by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchTriviaQuestions(amount = 1000) // Fetch 1000 questions
    }

    // Store filteredQuestions in a local variable
    val currentFilteredQuestions = filteredQuestions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedCategory == null) {
            // Category Selection Screen
            Text("Select a Category", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(16.dp))
            triviaQuestions?.map { it.category }?.distinct()?.forEach { category ->
                Button(
                    onClick = { viewModel.setSelectedCategory(category) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = category)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        } else {
            // Quiz Screen
            if (currentFilteredQuestions.isNullOrEmpty()) {
                Text(text = "No questions available for this category.", style = MaterialTheme.typography.h6)
            } else {
                val currentQuestion = currentFilteredQuestions[currentQuestionIndex]
                Text(text = "Question ${currentQuestionIndex + 1}", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = currentQuestion.question, style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(16.dp))

                // Display options
                val options = (currentQuestion.incorrect_answers + currentQuestion.correct_answer).shuffled()
                options.forEach { option ->
                    val isSelected = option == selectedAnswer
                    val isCorrect = option == currentQuestion.correct_answer
                    val backgroundColor = when {
                        showAnswer && isCorrect -> Color.Green
                        showAnswer && isSelected && !isCorrect -> Color.Red
                        else -> MaterialTheme.colors.surface
                    }

                    Button(
                        onClick = {
                            selectedAnswer = option
                            showAnswer = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
                    ) {
                        Text(text = option)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (showAnswer) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Correct Answer: ${currentQuestion.correct_answer}", style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (currentQuestionIndex < (currentFilteredQuestions.size - 1)) {
                                currentQuestionIndex++ // Move to the next question
                                selectedAnswer = null
                                showAnswer = false
                            } else {
                                // Handle end of quiz
                                currentQuestionIndex = 0 // Reset to the first question
                                selectedAnswer = null
                                showAnswer = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = if (currentQuestionIndex < (currentFilteredQuestions.size - 1)) "Next Question" else "Restart Quiz")
                    }
                }
            }
        }

        if (error != null) {
            Text(text = "Error: $error", color = Color.Red)
        }
    }
}