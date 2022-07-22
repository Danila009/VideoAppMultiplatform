package me.danbe.common.ui.screen.mainScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
actual fun getMainScreen(): @Composable () -> Unit {
    return {
        Text(text = "Test", color = Color.Red)
    }
}