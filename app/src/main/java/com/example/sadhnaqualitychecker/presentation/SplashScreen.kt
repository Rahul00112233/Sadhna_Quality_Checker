package com.example.sadhnaqualitychecker.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.sadhnaqualitychecker.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate("pledge") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.eb),
            contentDescription = "food",
            modifier = Modifier.fillMaxWidth()
        )
    }

}