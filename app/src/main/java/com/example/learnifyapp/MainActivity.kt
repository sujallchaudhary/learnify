package com.example.learnifyapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.learnifyapp.loginregister.AuthNavigation
import com.example.learnifyapp.loginregister.isLoggedIn
import com.example.learnifyapp.onboard.OnboardingScreen
import com.example.learnifyapp.onboard.OnboardingUtils
import com.example.learnifyapp.ui.theme.LearnifyAppTheme
import com.example.learnifyapp.ui.theme.MainView
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val onboardingUtils by lazy { OnboardingUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            LearnifyAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    if (onboardingUtils.isOnboardingCompleted()) {
                        AuthNavigation()
                    } else {
                        ShowOnboardingScreen()

                    }
                }
            }
        }


    }


    @Composable
    private fun ShowOnboardingScreen() {
        val scope = rememberCoroutineScope()
        OnboardingScreen {
            onboardingUtils.setOnboardingCompleted()
            scope.launch {
                setContent {
                    AuthNavigation()
                }
            }
        }


    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        LearnifyAppTheme {
            AuthNavigation()
        }
    }

}

//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//
//    private var isBound by mutableStateOf(false)
//    private lateinit var timerService: StudySessionTimerService
//    private val connection = object : ServiceConnection {
//        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
//            val binder = service as StudySessionTimerService.StudySessionTimerBinder
//            timerService = binder.getService()
//            isBound = true
//        }
//
//        override fun onServiceDisconnected(p0: ComponentName?) {
//            isBound = false
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Intent(this, StudySessionTimerService::class.java).also { intent ->
//            bindService(intent, connection, Context.BIND_AUTO_CREATE)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            if (isBound) {
//                LearnifyAppTheme {
//                    DestinationsNavHost(
//                        navGraph = NavGraphs.root,
//                        dependenciesContainerBuilder = {
//                            dependency(SessionScreenRouteDestination) { timerService }
//                        }
//                    )
//                }
//            }
//        }
//        requestPermission()
//    }
//
//    private fun requestPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
//                0
//            )
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unbindService(connection)
//        isBound = false
//    }
//}