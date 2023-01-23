package com.joel.comics.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.joel.comics.domain.model.comics.allcomics.AllComicsResult
import com.joel.comics.ui.presentation.views.destinations.ComicsDetailsScreenDestination
import com.joel.comics.utils.getComicImageLink
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun AllComicsCard(
    comics : AllComicsResult?,
    navigator: DestinationsNavigator
){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .padding(12.dp)
                .clickable {
                           navigator.navigate(ComicsDetailsScreenDestination(comics?.id!!))

                },
            shape = RoundedCornerShape(20.dp)
        ) {
            SubcomposeAsyncImage(
                model = getComicImageLink(comics?.thumbnail!!),
                contentDescription = comics.title,
                modifier = Modifier
                    .size(200.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            ) {
                val state = painter.state
                if(state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .scale(0.5f)
                    )
                }
                else{
                    SubcomposeAsyncImageContent()
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        Text(text = comics?.title!!)
    }
}