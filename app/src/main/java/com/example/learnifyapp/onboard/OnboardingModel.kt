package com.example.learnifyapp.onboard

import androidx.annotation.DrawableRes
import com.example.learnifyapp.R


sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {

    data object FirstPage : OnboardingModel(
        image = R.drawable.img_intro_1,
        title = "WELCOME TO LEARNIFY\nYour AI-Powered Learning Companion",
        description = "Learnify creates a customized study roadmap tailored to your goals. " +
                "Whether you're preparing for exams or mastering a new subject," +
                " we've got you covered! \uD83D\uDCDA✨"
    )

    data object SecondPage : OnboardingModel(
        image = R.drawable.img_intro_2,
        title = "Learn, Practice & Get Instant Help",
        description = "Access diverse resources, practice tests, " +
                "and AI-powered doubt solving to strengthen your concepts. " +
                "Never get stuck again! \uD83D\uDE80\uD83D\uDCA1",

    )

    data object ThirdPages : OnboardingModel(
        image = R.drawable.img_intro_3,
        title = "Boost Memory with Flashcards",
        description = "Master key concepts effortlessly using our AI-generated flashcards. " +
                "Spaced repetition helps you retain information longer and ace your exams! " +
                "\uD83C\uDFAF\uD83E\uDDE0"
    )


}