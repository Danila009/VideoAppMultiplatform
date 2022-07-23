package me.danbe.common

import androidx.compose.runtime.*
import me.danbe.common.di.*
import me.danbe.common.ui.navigation.Navigation
import me.danbe.common.ui.navigation.Screen
import me.danbe.common.ui.screen.getMainScreen
import org.kodein.di.compose.withDI
import ru.alexgladkov.odyssey.compose.extensions.screen

@Composable
fun App() = withDI(apiModule, viewModelsModule) {
    Navigation(
        startScreen = Screen.MainScreen.route
    ){
        screen(Screen.MainScreen.route){ getMainScreen() }
    }
}
