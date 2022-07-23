package me.danbe.common.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import me.danbe.common.ui.theme.primaryBackground
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator

@Composable
fun Navigation(
    startScreen: String,
    vararg providers: ProvidedValue<*>,
    navigationGraph: RootComposeBuilder.() -> Unit
) {

    val rootController = RootComposeBuilder()
        .apply(navigationGraph).build()

    rootController.backgroundColor = primaryBackground

    CompositionLocalProvider(
        *providers,
        LocalRootController provides rootController
    ) {
        ModalNavigator {
            Navigator(startScreen)
        }
    }
}