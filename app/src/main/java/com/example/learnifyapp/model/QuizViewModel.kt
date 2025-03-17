package com.example.learnifyapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnifyapp.RetrofitClient
import com.example.learnifyapp.data.TriviaQuestion
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private val _triviaQuestions = MutableLiveData<List<TriviaQuestion>?>(null)
    val triviaQuestions: LiveData<List<TriviaQuestion>?> get() = _triviaQuestions

    private val _filteredQuestions = MutableLiveData<List<TriviaQuestion>?>(null)
    val filteredQuestions: LiveData<List<TriviaQuestion>?> get() = _filteredQuestions

    private val _selectedCategory = MutableLiveData<String?>(null)
    val selectedCategory: LiveData<String?> get() = _selectedCategory

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> get() = _error

    fun fetchTriviaQuestions(amount: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.quizApiService.getTriviaQuestions(amount)
                if (response.isSuccessful) {
                    _triviaQuestions.value = response.body()?.results
                } else {
                    _error.value = "Failed to fetch trivia questions: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Network error: ${e.message}"
            }
        }
    }

    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
        _filteredQuestions.value = _triviaQuestions.value?.filter { it.category == category }
    }
}