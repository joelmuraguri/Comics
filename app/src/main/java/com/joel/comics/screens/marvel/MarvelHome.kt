package com.joel.comics.screens.marvel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.joel.comics.R
import com.joel.comics.components.Action
import com.joel.comics.components.CharacterItem
import com.joel.comics.components.SearchBar
import com.joel.comics.model.paginate.ErrorAction
import com.joel.comics.model.paginate.ItemLoading
import com.joel.comics.viewmodel.MarvelHomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Destination(start = true)
@Composable
fun MarvelHome(
    viewModel: MarvelHomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
){


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

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            CharacterList(viewModel, navigator)
        }
    }
}

@Composable
fun CharacterList(
    viewModel: MarvelHomeViewModel,
    navigator: DestinationsNavigator
    ) {

    val characterList = viewModel.characters.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .padding(12.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 12.dp,
            top = 16.dp,
            bottom = 16.dp
        )
    ){
        items(characterList){ character ->
            character?.let {
                CharacterItem(character = character, navigator)
            }
        }
        characterList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillParentMaxSize()
                                    .scale(0.4f)
                            )
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            ItemLoading()
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = characterList.loadState.refresh as LoadState.Error
                    item {
                        ErrorAction(
                            error = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = characterList.loadState.append as LoadState.Error
                    item {
                        ErrorAction(
                            error = e.error.localizedMessage!!,
                            onRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

