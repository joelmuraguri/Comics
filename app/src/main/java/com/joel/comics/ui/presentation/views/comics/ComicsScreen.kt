package com.joel.comics.ui.presentation.views.comics

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.joel.comics.core.ErrorItem
import com.joel.comics.core.ItemsLoading
import com.joel.comics.domain.model.comics.allcomics.AllComicsResult
import com.joel.comics.ui.presentation.components.AllComicsCard
import com.joel.comics.viewmodel.ComicsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.Flow

@Destination
@Composable
fun ComicsScreen(
    viewModel : ComicsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
){

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,

        ) {
        AllComicsItems(popularMovies = viewModel.allComics, navigator = navigator)
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllComicsItems(
    popularMovies : Flow<PagingData<AllComicsResult>>,
    navigator: DestinationsNavigator
){
    val lazyMovieItems = popularMovies.collectAsLazyPagingItems()

    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ){
        items(lazyMovieItems.itemCount){ index ->
            lazyMovieItems[index]?.let { comics ->
                AllComicsCard(
                    comics = comics,
                    navigator = navigator
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
                                    .fillParentMaxSize()
                                    .scale(0.5f)
                                    .fillParentMaxWidth()
                                    .fillParentMaxHeight(),
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

//@Preview(showBackground = true)
//@Composable
//fun AllComicsScreenPreview(){
//
//    ComicsScreen()
//
//}
