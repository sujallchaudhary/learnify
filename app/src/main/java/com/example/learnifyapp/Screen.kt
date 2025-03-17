package com.example.learnifyapp

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color


sealed class Screen(val title: String, val route: String) {

    sealed class BottomScreen(
        val bTitle: String, val bRoute: String, @DrawableRes val icon: Int
    ):Screen(bTitle,bRoute) {
        object Home : BottomScreen(
            "Home", "home",
            R.drawable.baseline_home_24
        )

        object Roadmap : BottomScreen(
            "Roadmap", "roadmap",
            R.drawable.baseline_insights_24
        )

        object Doubts : BottomScreen(
            "Doubts", "doubts",
            R.drawable.baseline_photo_camera_24
        )

        object Resources : BottomScreen(
            "Flashcards", "resources",
            R.drawable.baseline_menu_book_24
        )

        object Quizz : BottomScreen(
            "Quizz", "quizz",
            R.drawable.baseline_category_24
        )
    }

    sealed class  DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
        object Dashboard: DrawerScreen(
            "Dashboard",
            "dashboard",
            R.drawable.baseline_space_dashboard_24
        )
        object Profile: DrawerScreen(
            "Profile",
            "profile",
            R.drawable.baseline_account_circle_24
        )

        object PrivacyPolicy: DrawerScreen(
            "Privacy Policy",
            "privacy policy",
            R.drawable.baseline_verified_user_24
        )

        object Terms: DrawerScreen(
            "Terms & Conditions",
            "terms",
            R.drawable.baseline_recommend_24
        )

        object ContactUs: DrawerScreen(
            "Contact us",
            "contact us",
            R.drawable.baseline_language_24
        )

        object FAQS: DrawerScreen(
            "FAQS",
            "faqs",
            R.drawable.baseline_help_24
        )

        object Logout: DrawerScreen(
            "Logout",
            "logout",
            R.drawable.baseline_logout_24
        )
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Roadmap,
    Screen.BottomScreen.Doubts,
    Screen.BottomScreen.Resources,
    Screen.BottomScreen.Quizz
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Dashboard,
    Screen.DrawerScreen.Profile,
    Screen.DrawerScreen.PrivacyPolicy,
    Screen.DrawerScreen.Terms,
    Screen.DrawerScreen.ContactUs,
    Screen.DrawerScreen.FAQS,
    Screen.DrawerScreen.Logout
)