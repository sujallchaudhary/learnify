package com.example.learnifyapp.DrawerTheme

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learnifyapp.data.FAQItem
import com.example.learnifyapp.model.FAQsViewModel

@Composable
fun Faqs() {
    val viewModel: FAQsViewModel = viewModel()
    val faqs = viewModel.faqs

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(faqs) { faq ->
            FAQItem(faq = faq)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun FAQItem(faq: FAQItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = faq.question,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = faq.answer,
                fontSize = 16.sp
            )
        }
    }
}