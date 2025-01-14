package com.example.sadhnaqualitychecker.presentation

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sadhnaqualitychecker.R
import com.example.sadhnaqualitychecker.ui.theme.High
import com.example.sadhnaqualitychecker.ui.theme.Low
import com.example.sadhnaqualitychecker.ui.theme.Medium
import com.example.sadhnaqualitychecker.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Sadhna Quality Calculator", fontWeight = FontWeight.SemiBold, color = Color.White)
                },
                actions = {
                    IconButton(onClick = {navController.navigate("help")}) {
                        Icon(Icons.Default.MoreVert, contentDescription = "", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Orange)
            )
        }
    ){
        Column(modifier = Modifier.padding(it)){

            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                item {
                    CalculatorCard(
                        R.drawable.chanting,
                        name = "Chanting",
                        onCardClick = {
                            navController.navigate("chanting")
                        }
                    )
                }
                item {
                    CalculatorCard(
                        R.drawable.hearing1,
                        name = "Hearing",
                        onCardClick = {
                            navController.navigate("hearing")
                        }
                    )
                }
                item {
                    CalculatorCard(
                        R.drawable.reading,
                        name = "Reading",
                        onCardClick = {
                            navController.navigate("reading")
                        }
                    )
                }
                item {
                    CalculatorCard(
                        R.drawable.seva,
                        name = "Seva",
                        onCardClick = {
                            navController.navigate("seva")
                        }
                    )
                }
            }
            
        }
    }
}
@Composable
fun CalculatorCard(image: Int, name:String, onCardClick:()->Unit){
    Card(
        modifier = Modifier
            .padding(12.dp) // Padding around the card
            .clickable { onCardClick() },
        elevation = CardDefaults.elevatedCardElevation(8.dp), // Elevated shadow
        shape = RoundedCornerShape(16.dp), // Rounded corners for a smooth effect
        colors = CardDefaults.cardColors(com.example.sadhnaqualitychecker.ui.theme.Card) // Soft background color for a subtle look
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Padding inside the card for the content
                .fillMaxWidth(), // Fill the width of the parent
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Image with fixed aspect ratio
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp) // Set fixed height for image
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)) // Rounded corners for the image
                    .padding(bottom = 8.dp) // Space between the image and text
            )
            // Name with improved text styling
            Text(
                text = name,
                fontWeight = FontWeight.Bold, // Bold text for emphasis
                fontSize = 20.sp, // Adjusted font size for better readability
                color = Color.Black, // Text color
                style = MaterialTheme.typography.bodySmall, // Using Material typography for consistency
                modifier = Modifier.padding(top = 8.dp) // Space above the text
            )
        }
    }
}

@Composable
fun SliderWithLabel(label: String, value: Float, onValueChange: (Float) -> Unit) {
    Column {
        Text("$label: ${"%.0f".format(value * 100)}%", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            steps = 19,
            colors = SliderDefaults.colors(Orange, Orange)
        )
    }
}
@Composable
fun SliderWithInterval(label: String, value: Float, onValueChange: (Float) -> Unit) {
    Column {
        Text("$label: ${"%.0f".format(value)} Round(s)", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..64f,
            steps = 63,
            colors = SliderDefaults.colors(Orange, Orange)
        )
    }
}
@Composable
fun CheckboxWithLabel(label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.scale(0.8f),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Orange,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = Orange.copy(alpha = 0.5f),
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun DropdownMenuWithLabel(
    label: String,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Opportunity to please Lord Krsna","Duty","Forced")

    Column {
        Text(text = label)
        Box {
            TextField(
                value = selectedValue,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )
            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        },
                        text = {Text(option)}
                    )
                }
            }
        }
    }
}

