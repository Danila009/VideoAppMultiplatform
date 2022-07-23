package me.danbe.common.ui.screen.mainScreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
//import com.example.exo_player_compose.exoPlayerCompose.ExoPlayer
//import com.example.exo_player_compose.exoPlayerCompose.model.exoParameters
//import com.example.exo_player_compose.exoPlayerCompose.state.VideoPlayerPausePlayState
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.danbe.common.data.network.common.Response
import me.danbe.common.data.network.model.Video
import me.danbe.common.ui.theme.primaryBackground
import me.danbe.common.ui.view.Image
import org.kodein.di.compose.rememberInstance

@Composable
fun MainScreen() {

    val scope = rememberCoroutineScope()

    val mainViewModel by rememberInstance<MainViewModel>()

    var responseVideo: Response<List<Video>> by remember { mutableStateOf(Response.Loading()) }

    scope.launch {
        mainViewModel.responseVideos.onEach {
            responseVideo = it
        }.collect()
    }

    LazyColumn {
        when(responseVideo){
            is Response.Error -> {
                item {
                    Text(text = responseVideo.message.toString())
                }
            }
            is Response.Loading -> {
                item { CircularProgressIndicator() }
            }
            is Response.Succes -> {
                items(responseVideo.data ?: emptyList()){ item ->

                    var imageOnLongPress by rememberSaveable{ mutableStateOf(false) }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (imageOnLongPress){
//                            ExoPlayer(
//                                modifier = Modifier
//                                    .height(250.dp)
//                                    .fillMaxWidth(),
//                                parameters = exoParameters {
//                                    url = item.videoUrl
//                                    useController = false
//                                    statePlayPause = VideoPlayerPausePlayState.PLAY
//                                }
//                            )
                        }else {
                            Image(
                                url = item.previewsUrl,
                                modifier = Modifier
                                    .height(250.dp)
                                    .padding(
                                        horizontal = 0.dp,
                                        vertical = 5.dp
                                    )
                                    .fillMaxWidth()
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onLongPress = {
                                                imageOnLongPress = !imageOnLongPress
                                            }
                                        )
                                    }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                url = item.channel.icon,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(5.dp)
                                    .clip(AbsoluteRoundedCornerShape(90.dp))
                            )

                            Column {
                                Text(
                                    text = item.title,
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 5.dp
                                        )
                                        .fillMaxWidth(),
                                    fontWeight = FontWeight.W900,
                                    color = primaryBackground
                                )

                                Text(
                                    text = item.channel.title,
                                    modifier = Modifier.padding(
                                        start = 5.dp,
                                        end = 5.dp,
                                        bottom = 5.dp
                                    ),
                                    fontWeight = FontWeight.W100
                                )
                            }
                        }

                        Divider()
                    }
                }
            }
        }
    }
}