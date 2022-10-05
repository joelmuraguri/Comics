package com.joel.comics.screens.marvel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joel.comics.model.marvelmodel.marvdata.marvresponse.character.ResultX
import com.joel.comics.model.network.Resource
import com.joel.comics.viewmodel.DetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),

){
    val characterId = 0

    val marvelCharacter = produceState<Resource<ResultX>>(initialValue = Resource.Loading()){
        value = viewModel.getMarvelHero(characterId = characterId)
    }.value

    DetailsWrapper(characterInfo = marvelCharacter)

}

@Composable
fun DetailsWrapper(
    characterInfo: Resource<ResultX>
){

    when(characterInfo){
        is Resource.Success -> {
            CharacterInfoComposable(characterInfo = characterInfo.data!!)

        }

        is Resource.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .scale(0.4f)
                )
            }
        }

        is Resource.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize())
            {
                Text(
                    text = "Unknown Error",
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun CharacterInfoComposable(
    characterInfo: ResultX
){

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = characterInfo.description,
            fontSize = 30.sp
        )
    }

}