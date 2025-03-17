package com.example.learnifyapp.ui.theme

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learnifyapp.DrawerTheme.ContactUs
import com.example.learnifyapp.DrawerTheme.Dashboard
import com.example.learnifyapp.DrawerTheme.Faqs
import com.example.learnifyapp.DrawerTheme.Logout
import com.example.learnifyapp.DrawerTheme.PrivacyPolicy
import com.example.learnifyapp.DrawerTheme.Profile
import com.example.learnifyapp.DrawerTheme.Terms
import com.example.learnifyapp.MainViewModel
import com.example.learnifyapp.R
import com.example.learnifyapp.Screen
import com.example.learnifyapp.screensInBottom
import com.example.learnifyapp.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MainView() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val viewModel: MainViewModel = viewModel()

    // To find out current view
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = remember {
        viewModel.currentScreen.value
    }

    val title = remember {
        mutableStateOf(currentScreen.title)
    }

    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
            BottomNavigation(Modifier.wrapContentSize(), backgroundColor = select) {
                screensInBottom.forEach { item ->
                    val isSelected = currentRoute == item.bRoute
                    Log.d(
                        "Navigation",
                        "Item: ${item.bTitle}, Current Route: $currentRoute, Is Selected: $isSelected"
                    )
                    val tint = if (isSelected) golden else Color.White
                    BottomNavigationItem(
                        selected = currentRoute == item.bRoute,
                        onClick = {
                            controller.navigate(item.bRoute)
                            title.value = item.bTitle
                        },
                        icon = {

                            Icon(
                                tint = tint,
                                contentDescription = item.bTitle,
                                painter = painterResource(id = item.icon),
                                modifier = Modifier.size(33.dp)
                            )
                        },
                        label = { Text(text = item.bTitle, color = tint, fontSize = 11.sp) },
                        selectedContentColor = select,

                        unselectedContentColor = Color.White

                    )
                }
            }
        }
    }


    ModalDrawer(
        drawerState = drawerState ,
        drawerContent = {
            // Your drawer content here
            if (scaffoldState.drawerState.isOpen) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable(
                            onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(AppBackground)
                    ) {
                        LazyColumn(
                            Modifier
                                .padding(16.dp)
                        ) {
                            items(screensInDrawer) { item ->
                                DrawerItem(false, item)
                                {
                                    scope.launch {
                                        scaffoldState.drawerState.close()
                                        controller.navigate(item.dRoute) {
                                            // Prevents multiple copies of the same destination
                                            popUpTo(controller.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }

                                        title.value = item.dTitle
                                    }

                                }
                            }
                        }
                    }
                }
            }
        },
        gesturesEnabled = drawerState.isOpen, // Enable gestures only when the drawer is open
        content = {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppBackground)
            ) {
                Scaffold(
                    bottomBar = bottomBar,
                    topBar = {
                        AppBarView(
                            title = "Learnify",
                            scaffoldState = scaffoldState,
                            scope = scope
                        )
                    },
                    scaffoldState = scaffoldState,
                    backgroundColor = AppBackground) {
                    androidx.compose.foundation.layout.Box(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        // Main content of the screen
                        Navigation(
                            navController = controller,
                            viewModel = viewModel,
                            pd = it
                        )
                    }


                }
            }
        },
        drawerShape = MaterialTheme.shapes.medium,
        drawerElevation = 16.dp,
        drawerBackgroundColor = select,
        scrimColor = Color.White.copy(alpha = 0.32f)
    )
}


@Composable
fun AppBarView(
    title: String,
    scaffoldState : ScaffoldState,
    scope : CoroutineScope

){
    TopAppBar(
        title =
        { Text(text = title,
            color = colorResource(id = R.color.black),
            fontSize = 25.sp,
            modifier = Modifier
        )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    tint = Color.Black,
                    contentDescription = null
                )
            }
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.AppBackground)
    )
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked : () -> Unit
){
    val background = if (selected) select else Color.Transparent
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable { onDrawerItemClicked() }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            tint = Color.Black,
            contentDescription = item.dTitle,
            modifier = Modifier
                .padding(end = 10.dp, top = 2.dp)
                .size(35.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h5,
            fontSize = 30.sp,
            color = Color.Black)
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues){

    NavHost(navController = navController as NavHostController,
        startDestination = Screen.BottomScreen.Home.route, modifier = Modifier.padding(pd) ){

        // Bottom Screen Icons
        composable(Screen.BottomScreen.Home.bRoute){
            Home(navController = navController)
        }
        composable(Screen.BottomScreen.Roadmap.bRoute){
            Roadmap(onBackPressed = {
                navController.popBackStack() // Navigate back to the previous screen
            })
        }

        composable(Screen.BottomScreen.Doubts.bRoute){
            Doubts()
        }

        composable(Screen.BottomScreen.Resources.bRoute){
            Resources()
        }

        composable(Screen.BottomScreen.Quizz.bRoute){
            Quizz()
        }

        // Drawer Screen Icons
        composable(Screen.DrawerScreen.Dashboard.dRoute){
            Dashboard()
        }

        composable(Screen.DrawerScreen.Profile.dRoute){
            Profile()
        }

        composable(Screen.DrawerScreen.PrivacyPolicy.dRoute){
            PrivacyPolicy()
        }

        composable(Screen.DrawerScreen.Terms.dRoute){
            Terms()
        }

        composable(Screen.DrawerScreen.ContactUs.dRoute){
            ContactUs()
        }

        composable(Screen.DrawerScreen.FAQS.dRoute){
            Faqs()
        }

        composable(Screen.DrawerScreen.Logout.dRoute){
            Logout(navController)
        }
    }

}