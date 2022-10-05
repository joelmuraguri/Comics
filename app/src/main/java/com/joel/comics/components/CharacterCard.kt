package com.joel.comics.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.joel.comics.screens.marvel.destinations.DetailsScreenDestination
import com.joel.comics.utils.getImageLink
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun CharacterItem(
    character: com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.Result,
    navigator: DestinationsNavigator
){
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .padding(12.dp)
            .clickable {
                navigator.navigate(DetailsScreenDestination)

            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = getImageLink(character.thumbnail),
                contentDescription = character.name,
                modifier = Modifier
                    .size(300.dp)

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
            Text(text = character.name)
        }
    }
}