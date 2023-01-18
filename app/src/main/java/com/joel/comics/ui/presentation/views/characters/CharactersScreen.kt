package com.joel.comics.ui.presentation.views.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.joel.comics.core.ErrorItem
import com.joel.comics.core.ItemsLoading
import com.joel.comics.domain.model.characters.allcharacters.AllCharactersResult
import com.joel.comics.ui.presentation.components.AllCharactersCard
import com.joel.comics.viewmodel.CharactersViewModel
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.Flow


@Destination
@Composable
fun CharactersScreen(viewModel: CharactersViewModel = hiltViewModel()){

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,

        ) {
        AllCharacterItems(popularMovies = viewModel.allCharacters)
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllCharacterItems(
    popularMovies : Flow<PagingData<AllCharactersResult>>
){
    val lazyMovieItems = popularMovies.collectAsLazyPagingItems()

    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ){
        items(lazyMovieItems.itemCount){ index ->
            lazyMovieItems[index]?.let { character ->
                AllCharactersCard(
                    character = character,
                )
            }
        }

        lazyMovieItems.apply {
            when{

                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .scale(0.5f)
                                    .align(Alignment.Center),
                                color = Color.Yellow
                            )
                        }

                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        ItemsLoading(modifier = Modifier.fillMaxWidth())
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            error.error.localizedMessage?.let {
                                ErrorItem(
                                    error = it,
                                    onRetry = { refresh() },

                                    )
                            }
                        }
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = lazyMovieItems.loadState.append as LoadState.Error
                    item {
                        error.error.localizedMessage?.let { ErrorItem(onRetry = { retry() }, error = it) }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllComicsScreenPreview(){

    CharactersScreen()

}

