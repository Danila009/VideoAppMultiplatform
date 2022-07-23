package me.danbe.common.ui.screen

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.danbe.common.data.network.common.Response
import me.danbe.common.data.network.model.Video
import me.danbe.common.ui.screen.mainScreen.MainViewModel
import me.danbe.common.ui.theme.primaryText
import me.danbe.common.ui.view.Image
import org.kodein.di.compose.localDI
import org.kodein.di.instance

@Composable
fun MainScreen() {

    val scope = rememberCoroutineScope()

    val di = localDI()
    val viewModel by di.instance<MainViewModel>()

    var video:Response<List<Video>> by remember { mutableStateOf(Response.Loading()) }


    scope.launch {
        viewModel.responseVideos.onEach {
            video = it
        }.collect()
    }


    LazyColumn {
        when(video){
            is Response.Error -> {
                item {
                    Text(
                        text = video.message ?: "Error"
                    )
                }
            }
            is Response.Loading -> {
                item { CircularProgressIndicator() }
            }
            is Response.Succes -> {
                items(video.data ?: emptyList()){ item ->

                    Image(
                        url = item.previewsUrl,
                        modifier = Modifier.size(300.dp)
                    )

                    Text(
                        text = item.title,
                        color = primaryText
                    )
                }
            }
        }
    }
}