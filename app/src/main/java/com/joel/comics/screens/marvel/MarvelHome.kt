package com.joel.comics.screens.marvel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.joel.comics.R
import com.joel.comics.components.Action
import com.joel.comics.components.SearchBar
import com.joel.comics.model.marvelmodel.MarvelListEntry
import com.joel.comics.viewmodel.MarvelHomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Destination
@Composable
fun MarvelHome(
    marvelHomeViewModel: MarvelHomeViewModel = hiltViewModel(),
){


    LaunchedEffect(key1 = true){
        marvelHomeViewModel.loadMarvelCharacters()
    }


    val state = rememberCollapsingToolbarScaffoldState()
    val textSize = (18 + ( 30-12 ) * state.toolbarState.progress).sp

    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .pin()
                    .height(150.dp)
            )

            
            Image(
                painter = painterResource(id = R.drawable.marvel_one),
                contentDescription = "avengers",
                contentScale = ContentScale.Fit,
                alpha = if (textSize.value == 18f) 0f else 1f
            )

            Box(
                modifier = Modifier
                    .road(whenCollapsed = Alignment.TopEnd, whenExpanded = Alignment.TopEnd),
            ) {
                Action()
            }
            
            Text(
                text = "Home of Marvel Heroes",
                modifier = Modifier
                    .road(whenCollapsed = Alignment.TopStart, whenExpanded = Alignment.BottomCenter),
                style = TextStyle(color = Color.White, fontSize = textSize),
            )
        })
    {
        CharacterList()
    }
}

@Composable
fun CharacterList(
    marvelHomeViewModel: MarvelHomeViewModel = hiltViewModel(),

){

    val isLoading =  marvelHomeViewModel.isLoading
    val loadError =  marvelHomeViewModel.loadedError

    val characters = marvelHomeViewModel.marvelList

    LazyColumn(
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.fillMaxSize()
    ){
        item {
            Box(modifier = Modifier
                .fillMaxWidth()){
                SearchBar("Search for a character"){

                }
            }
        }

        items(characters.value){ character ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SubcomposeAsyncImage(
                        model = character.imageUrl,
                        contentDescription = character.characterName,
                        modifier = Modifier.size(200.dp)
                    )
                    {
                        val state = painter.state
                        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                            CircularProgressIndicator()
                        }
                        else{
                            SubcomposeAsyncImageContent()
                        }
                    }
                    Text(
                        text = character.characterName,
                        modifier = Modifier
                            .padding(12.dp)
                    )
                }
            }
        }
    }


    Box(
        contentAlignment = Alignment.Center
    ) {
//        if (isLoading){
//            CircularProgressIndicator()
//        }
//        else{
//            RetrySection(error = loadError){
//                marvelHomeViewModel.loadMarvelCharacters()
//            }
//        }
    }
}


