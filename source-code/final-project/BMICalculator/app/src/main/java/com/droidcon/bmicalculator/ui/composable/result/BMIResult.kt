package com.droidcon.bmicalculator.ui.composable.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BMIResult(result: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF04040c))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "%.2f".format(result),
            color = Color.Yellow,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        val resultText = if (result < 18.5) {
            "Under Weight"
        } else if (result >= 18.5 && result < 24.9) {
            "Healthy"
        } else if (result >= 24.9 && result < 30) {
            "Overweight"
        } else if (result >= 30) {
            "Suffering from Obesity"
        } else {
            "Unknown"
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = resultText,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}