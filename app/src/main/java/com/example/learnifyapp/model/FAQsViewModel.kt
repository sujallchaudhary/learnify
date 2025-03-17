package com.example.learnifyapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.learnifyapp.data.FAQItem

class FAQsViewModel : ViewModel() {
    var faqs by mutableStateOf<List<FAQItem>>(emptyList())
        private set

    init {
        // Initialize the list of FAQs
        faqs = listOf(
            FAQItem(
                question = "What is Learnify?",
                answer = "Learnify is an education platform that helps students study with the help of AI."
            ),
            FAQItem(
                question = "How do I create an account?",
                answer = "You can create an account by clicking on the 'Register' button on the login screen."
            ),
            FAQItem(
                question = "How do I reset my password?",
                answer = "You can reset your password by clicking on the 'Forgot Password' link on the login screen."
            ),
            FAQItem(
                question = "Is Learnify free to use?",
                answer = "Yes, Learnify is free to use for all students."
            ),
            FAQItem(
                question = "How do I contact support?",
                answer = "You can contact support by sending an email to support@learnify.com."
            )
        )
    }
}