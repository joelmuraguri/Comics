package com.joel.comics.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.joel.comics.domain.model.series.allseries.AllSeriesResult
import com.joel.comics.utils.getSeriesImageLink

@Composable
fun AllSeriesCard(
    series : AllSeriesResult
){

    Card(
        elevation = 5.dp,
        modifier = Modifier
            .padding(12.dp)
            .clickable {

            },
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {

            SubcomposeAsyncImage(
                model = getSeriesImageLink(series.thumbnail),
                contentDescription = series.title,
                modifier = Modifier

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
            Text(text = series.title)
        }
    }
}