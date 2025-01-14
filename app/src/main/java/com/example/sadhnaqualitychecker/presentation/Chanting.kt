package com.example.sadhnaqualitychecker.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sadhnaqualitychecker.ui.theme.High
import com.example.sadhnaqualitychecker.ui.theme.Low
import com.example.sadhnaqualitychecker.ui.theme.Medium
import com.example.sadhnaqualitychecker.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChantingScreen(navController: NavController) {

    var prayerfulMood by remember { mutableStateOf(0f) }
    var attentiveness by remember { mutableStateOf(0f) }
    var sleepiness by remember { mutableStateOf(0f) }
    var sittingPercentage by remember { mutableStateOf(0f) }
    var backStraightPercentage by remember { mutableStateOf(0f) }
    var multiTaskingJapa by remember { mutableStateOf(false) }
    var vaishnavaApradh by remember { mutableStateOf(false) }
    var naamApradh by remember { mutableStateOf(false) }
    var morningRounds by remember { mutableStateOf(0f) }
    var eveningRounds by remember { mutableStateOf(0f) }

    val weights = mapOf(
        "Prayerful Mood" to 20,
        "Attentiveness" to 25,
        "Sleepiness" to -10,
        "Sitting Japa" to 15,
        "Back Straight" to 10,
        "Multi-tasking Japa" to -15,
        "Vaishnava Aparadh" to -20,
        "Naam Aparadh" to -30,
        "Morning Rounds" to 30
    )
    var state =  rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Chanting Quality Calculator", fontWeight = FontWeight.SemiBold, color = Color.White)
            },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
                ,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Orange))
        }
    ){

        Column(modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 12.dp).verticalScroll(state)) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "Positives", color = Color.White, modifier = Modifier
                    .fillMaxWidth()
                    .background(Orange.copy(0.5f))
            )
            SliderWithInterval(
                label = "Morning Rounds",
                value = morningRounds,
                onValueChange = { morningRounds = it }
            )
            SliderWithInterval(
                label = "Beyond 8 AM",
                value = eveningRounds,
                onValueChange = { eveningRounds = it }
            )
            SliderWithLabel(
                label = "Prayerful Mood",
                value = prayerfulMood,
                onValueChange = { prayerfulMood = it }
            )
            SliderWithLabel(
                label = "Attentiveness",
                value = attentiveness,
                onValueChange = { attentiveness = it }
            )

            SliderWithLabel(
                label = "Sitting Japa",
                value = sittingPercentage,
                onValueChange = {
                    sittingPercentage = it
                }
            )
            if (sittingPercentage > 0) {
                SliderWithLabel(
                    label = "Back Straight",
                    value = backStraightPercentage,
                    onValueChange = { backStraightPercentage = it }
                )
            }
            Text(
                "Negatives", color = Color.White, modifier = Modifier
                    .fillMaxWidth()
                    .background(Orange.copy(0.5f))
            )
            Spacer(modifier = Modifier.height(4.dp))
            SliderWithLabel(
                label = "Sleepiness",
                value = sleepiness,
                onValueChange = { sleepiness = it }
            )
            CheckboxWithLabel(
                label = "Multi-tasking Japa?",
                isChecked = multiTaskingJapa,
                onCheckedChange = { multiTaskingJapa = it }
            )
            CheckboxWithLabel(
                label = "Vaishnava Aparadh?",
                isChecked = vaishnavaApradh,
                onCheckedChange = { vaishnavaApradh = it }
            )
            CheckboxWithLabel(
                label = "Naam Aparadh?",
                isChecked = naamApradh,
                onCheckedChange = { naamApradh = it }
            )


            // Calculate the total score
            val totalScore = calculateChantingScore(
                prayerfulMood = prayerfulMood,
                attentiveness = attentiveness,
                sleepiness = sleepiness,
                backStraightPercentage = backStraightPercentage,
                sittingPercentage = sittingPercentage,
                multiTaskingJapa = multiTaskingJapa,
                vaishnavaApradh = vaishnavaApradh,
                naamApradh = naamApradh,
                morningRounds = morningRounds,
                weights = weights,
                eveningRounds = eveningRounds
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Chanting Score: $totalScore%",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = when (totalScore) {
                    in 0..50 -> Low
                    in 51..75 -> Medium
                    else -> High
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun calculateChantingScore(
    prayerfulMood: Float,
    attentiveness: Float,
    sleepiness: Float,
    backStraightPercentage: Float,
    sittingPercentage: Float,
    multiTaskingJapa: Boolean,
    vaishnavaApradh: Boolean,
    naamApradh: Boolean,
    morningRounds: Float,
    eveningRounds: Float,
    weights: Map<String, Int>
): Int {
    val maxScore = weights.values.filter { it > 0 }.sum()
    var totalScore = 0f

    val morningWeight = weights["Morning Rounds"] ?: 0 // Higher weight for morning
    val eveningWeight = weights["Back Straight"] ?: (morningWeight / 2) // Evening rounds have half the weight of morning rounds

    val totalRounds = morningRounds + eveningRounds  //5+5

    if (totalRounds > 0) {
        val morningContribution = (morningRounds / totalRounds) * morningWeight  //5/10  * 30
        val eveningContribution = (eveningRounds / totalRounds) * eveningWeight

        totalScore += morningContribution + eveningContribution
    }

    totalScore += prayerfulMood * (weights["Prayerful Mood"] ?: 0)
    totalScore += attentiveness * (weights["Attentiveness"] ?: 0)
    totalScore += (sleepiness) * (weights["Sleepiness"] ?: 0)
    totalScore += sittingPercentage * (weights["Sitting Japa"] ?: 0)
    totalScore += (backStraightPercentage ) * sittingPercentage * (weights["Back Straight"] ?: 0)
    if (multiTaskingJapa) totalScore += weights["Multi-tasking Japa"] ?: 0
    if (vaishnavaApradh) totalScore += weights["Vaishnava Aparadh"] ?: 0
    if (naamApradh) totalScore += weights["Naam Aparadh"] ?: 0
    else totalScore += weights["Other Time"] ?: 0

    return ((totalScore / maxScore) * 100).toInt().coerceIn(0, 100)
}