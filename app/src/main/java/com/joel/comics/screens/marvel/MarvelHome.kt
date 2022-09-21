package com.joel.comics.screens.marvel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.joel.comics.viewmodel.MarvelHomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import androidx.compose.runtime.*
import com.joel.comics.components.RetrySection


@Destination
@Composable
fun MarvelHome(
    viewModel: MarvelHomeViewModel = hiltViewModel(),
){


    LaunchedEffect(key1 = true){
        viewModel.loadMarvelCharacters()
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
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 12.dp
                )
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)

            ) {
                SearchBar() {
                    viewModel.searchCharacter(it)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            CharacterList()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterList(
    viewModel: MarvelHomeViewModel = hiltViewModel(),
    ) {

    val marvelList by remember { viewModel.marvelList }
    val isLoading by remember { viewModel.isLoading }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadedError }
    val isSearching by remember { viewModel.isSearching }


    val characters = viewModel.marvelList

    LazyVerticalGrid(
        cells = GridCells.Fixed(2), contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {

            items(characters.value) { character ->
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
                        )
                        {
                            val state = painter.state
                            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .scale(0.5f)
                                )
                            } else {
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
    )
}


