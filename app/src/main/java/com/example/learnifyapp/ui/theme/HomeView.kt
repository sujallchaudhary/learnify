package com.example.learnifyapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnifyapp.R

@Composable
fun Home(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Courses",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Carousel
        LazyRow {
            items(carouselItems) { item ->
                CarouselItem(item)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Banners
        BannerSection()

        Spacer(modifier = Modifier.height(20.dp))

        // Navigation Blocks
        NavigationBlocks(navController)
    }
}

@Composable
fun CarouselItem(item: CarouselItem) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = item.title,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
fun BannerSection() {
    Column {
        Text(
            text = "Featured",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            items(bannerItems) { item ->
                BannerItem(item)
            }
        }
    }
}

@Composable
fun BannerItem(item: BannerItem) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = item.title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )
    }
}

@Composable
fun NavigationBlocks(navController: NavController) {
    Column {
        Text(
            text = "Explore",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NavigationBlock("Profile", R.drawable.baseline_account_circle_24, navController, "profile")
            NavigationBlock("Doubts", R.drawable.baseline_photo_camera_24, navController, "doubts")
            NavigationBlock("Flashcards", R.drawable.baseline_menu_book_24, navController, "resources")
            NavigationBlock("Quizz", R.drawable.baseline_category_24, navController, "quizz")
            NavigationBlock("FAQs", R.drawable.baseline_help_24, navController, "faqs")
            NavigationBlock("Dashboard", R.drawable.baseline_space_dashboard_24, navController, "dashboard")
        }
    }
}

@Composable
fun NavigationBlock(title: String, iconRes: Int, navController: NavController, route: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { navController.navigate(route) }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// Data classes for carousel and banner items
data class CarouselItem(val imageRes: Int, val title: String)
data class BannerItem(val imageRes: Int, val title: String)

// Sample data
val carouselItems = listOf(
    CarouselItem(R.drawable.science, title = ""),
    CarouselItem(R.drawable.english, title = ""),
    CarouselItem(R.drawable.maths, title = ""),
    CarouselItem(R.drawable.economics, title = "")

)

val bannerItems = listOf(
    BannerItem(R.drawable.img_books, "Doubt Solving"),
    BannerItem(R.drawable.img_lamp, "Flashcards"),
    BannerItem(R.drawable.img_tasks, "Quizzes")
)