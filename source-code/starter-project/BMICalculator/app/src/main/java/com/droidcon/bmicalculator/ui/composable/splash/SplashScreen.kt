package com.droidcon.bmicalculator.ui.composable.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.droidcon.bmicalculator.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LaunchedEffect(key1 = null) {
        delay(1500)
        navController.popBackStack()
        navController.navigate("bmiform") {
            launchSingleTop = true
        }
    }
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splashbackground),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "BMI Calculator",
            color = Color.White,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}