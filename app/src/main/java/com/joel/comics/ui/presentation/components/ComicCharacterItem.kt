package com.joel.comics.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberImagePainter
import com.joel.comics.domain.model.comics.comicharacters.ComicCharactersResult

@Composable
fun ComicCharacterItem(
    character : ComicCharactersResult,
    characterName: String,
    characterImageUrl: String
){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SubcomposeAsyncImage(
            model = characterImageUrl,
            contentDescription = characterName,
            modifier = Modifier
                .fillMaxSize()
                .size(90.dp)
                .padding(8.dp)
                .clip(CircleShape),
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
        Text(
            text = characterName,
            color = Color.LightGray,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 11.sp
        )
    }

}