package com.numaan.assesmentrngdev.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.numaan.assesmentrngdev.utils.PreferencesManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MainNavHost()
        }
    }
}

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    //navigation graph
    NavHost(
        navController = navController,
        startDestination = if (PreferencesManager.isLoggedIn()) "home" else "login"
    ) {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                //Navigate to the home screen on successful login
                navController.navigate("home") {
                    //Pop the login screen off the back stack, so it won't be available when pressing the back button
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("home") {
            HomeScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

    }
}