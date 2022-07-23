package me.danbe.common.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import io.ktor.http.*

@Composable
fun Image(
    modifier: Modifier = Modifier,
    url:String
){
    val imageResource = lazyPainterResource(data = Url(url))

    SampleImage(
        resource = imageResource,
        modifier = modifier
    )
}

@Composable
private fun SampleImage(resource: Resource<Painter>, modifier: Modifier = Modifier) {

    KamelImage(
        resource = resource,
        contentDescription = null,
        modifier = modifier,
        onLoading = {
            Box(modifier, contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        },
        onFailure = {
            print(it.message)
            Text(text = it.message.toString())
        },
        crossfade = true,
    )

}