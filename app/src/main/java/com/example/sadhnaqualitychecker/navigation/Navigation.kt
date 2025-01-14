package com.example.sadhnaqualitychecker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sadhnaqualitychecker.presentation.ChantingScreen
import com.example.sadhnaqualitychecker.presentation.HearingScreen
import com.example.sadhnaqualitychecker.presentation.HomeScreen
import com.example.sadhnaqualitychecker.presentation.ISKCONPracticePage
import com.example.sadhnaqualitychecker.presentation.PledgeScreenWithTyping
import com.example.sadhnaqualitychecker.presentation.Reading
import com.example.sadhnaqualitychecker.presentation.SevaScreen
import com.example.sadhnaqualitychecker.presentation.SplashScreen

@Composable
fun Navigation(){
    val navController: NavHostController = rememberNavController()
    NavHost(navController, startDestination = "splash") {

        composable("splash"){
            SplashScreen(navController)
        }
        composable("pledge"){
            PledgeScreenWithTyping(navController)
        }
        composable("home"){
            HomeScreen(navController)
        }
        composable("help"){
            ISKCONPracticePage(navController)
        }
        composable("chanting"){
            ChantingScreen(navController)
        }
        composable("reading"){
            Reading(navController)
        }
        composable("hearing"){
            HearingScreen(navController)
        }
        composable("seva"){
            SevaScreen(navController)
        }
    }
}