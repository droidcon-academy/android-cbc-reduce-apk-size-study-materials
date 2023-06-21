package com.droidcon.bmicalculator.ui.composable.bmi

import Gender
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class BMIViewModel {
    var isMaleSelected by mutableStateOf(false)
    var isFemaleSelected by mutableStateOf(false)
    var userHeight by mutableStateOf(100f)
    var weight by mutableStateOf(50)
    var age by mutableStateOf(20)

    fun selectGender(gender: Gender) {
        isMaleSelected = gender == Gender.MALE
        isFemaleSelected = gender == Gender.FEMALE
    }

    fun setHeight(newHeight: Float) {
        userHeight = newHeight
    }

    fun incrementWeight() {
        weight++
    }

    fun decrementWeight() {
        if (weight != 0) {
            weight--
        }
    }

    fun incrementAge() {
        age++
    }

    fun decrementAge() {
        if (age != 0) {
            age--
        }
    }

    fun calculateBMI(): Float {
        return (weight) / (userHeight / 100 * userHeight / 100)
    }

    fun unUsedMethod() {
        // This method does nothing
    }
}