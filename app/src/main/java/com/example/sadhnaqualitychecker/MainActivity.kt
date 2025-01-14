package com.example.sadhnaqualitychecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sadhnaqualitychecker.navigation.Navigation
import com.example.sadhnaqualitychecker.ui.theme.SadhnaQualityCheckerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SadhnaQualityCheckerTheme {
                Navigation()
            }
        }
    }
}
