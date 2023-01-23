package com.joel.comics.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.joel.comics.utils.getComicImageLink
import com.joel.comics.viewmodel.ComicsViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.max
import kotlin.math.min

@Composable
fun ComicImageBanner(
    navigator: DestinationsNavigator,
    scrollState : LazyListState,
    comicImageURL : String,
    comicName : String
){

    val context = LocalContext.current

    val imageHeight = 420.dp -50.dp

    val maxOffset = with(LocalDensity.current) {
        imageHeight.roundToPx()
    } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        modifier = Modifier
            .height(420.dp)
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column {
            Box {

                SubcomposeAsyncImage(
                    model = comicImageURL,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(imageHeight)
                        .graphicsLayer {
                            alpha = 1f - offsetProgress
                        },
                    contentScale = ContentScale.Crop,


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

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.3f, Transparent),
                                    Pair(1.5f, Black)
                                )
                            )
                        )
                )
                ComicName(comicName = comicName)

            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
    ) {
        CircularBackButtons(
            onClick = {
                navigator.popBackStack()
            }
        )
    }
}