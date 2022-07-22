package me.danbe.common

import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.danbe.common.data.network.Api
import me.danbe.common.di.apiModule
import me.danbe.common.ui.navigation.Navigation
import org.kodein.di.compose.localDI
import org.kodein.di.compose.withDI
import org.kodein.di.instance
import ru.alexgladkov.odyssey.compose.extensions.screen

@Composable
fun App(

)
= withDI(
    apiModule
)
{

    Navigation(
        startScreen = "main_screen"
    ){
        screen("main_screen"){
//            getMainScreen()

            val di = localDI()
            val api by di.instance<Api>()

            var text by remember { mutableStateOf("Hello, World!") }

            CoroutineScope(Dispatchers.IO).launch{
                api.getVideos().collect {
                    text = (it.data ?: it.message).toString()
                }
            }

            Text(text = text)

        }
    }
}
