package com.joel.comics.screens.dc

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun DCHome(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Card(
               modifier = Modifier
                   .size(400.dp)
            ) {
                SubcomposeAsyncImage(
                    model = "https://joel14290.pythonanywhere.com/media/2013-BMW-X5-.jpg",
                    contentDescription = "bmw x5",
                    modifier = Modifier
                        .height(300.dp)
                        .width(300.dp)
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        CircularProgressIndicator()
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

            Text(text = "Home to Superman, Batman and WonderWoman")
        }
    }

}
