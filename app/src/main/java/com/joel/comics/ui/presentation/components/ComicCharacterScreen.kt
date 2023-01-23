package com.joel.comics.ui.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.joel.comics.core.StandardToolbar
import com.joel.comics.domain.model.comics.comicharacters.ComicCharacters
import com.joel.comics.utils.getComicCharacterImageLink
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComicCharacterScreen(
    characters : ComicCharacters,
    navigator: DestinationsNavigator
){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        StandardToolbar(
            navigator = navigator,
            title = {
                Text(
                    text = "Characters",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            showBackArrow = true
        )

        Timber.d(getComicCharacterImageLink(characters.data.results[0].thumbnail))

//        LazyVerticalGrid(
//            cells = GridCells.Fixed(2),
//            contentPadding = PaddingValues(16.dp)
//        ) {
//            items(characters.data.results.size) { index ->
//
//                Timber.d(characters.toString())
//
//                ComicCharacterItem(
//                    characterImageUrl = getComicCharacterImageLink(),
//                    characterName = characters.
//                )
//            }
//        }
    }
}