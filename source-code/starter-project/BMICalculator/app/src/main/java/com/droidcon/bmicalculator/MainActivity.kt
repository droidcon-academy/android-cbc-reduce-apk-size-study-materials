package com.droidcon.bmicalculator

import BMIForm
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.droidcon.bmicalculator.ui.composable.bmi.BMIViewModel
import com.droidcon.bmicalculator.ui.composable.result.BMIResult
import com.droidcon.bmicalculator.ui.composable.splash.SplashScreen
import com.droidcon.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            actionBar?.hide()
            val navController = rememberNavController()
            val bmiViewModel = BMIViewModel()

            BMICalculatorTheme {
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController = navController) }
                    composable("bmiform") { BMIForm(bmiViewModel, navController) }
                    composable(
                        "result/{bmiresult}",
                        arguments = listOf(navArgument("bmiresult") { type = NavType.FloatType })
                    ) { backStackEntry ->
                        BMIResult(backStackEntry.arguments?.getFloat("bmiresult") ?: 0.0F)
                    }
                }
            }
        }
    }

    fun unUsedMethod() {
        // This method does nothing
    }
}