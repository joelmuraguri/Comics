package com.joel.comics.model.paginate

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListViewLoading(
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        contentAlignment = Center

    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .scale(0.5f)
                .width(42.dp)
                .height(20.dp)
                .wrapContentWidth(CenterHorizontally)
                .padding(16.dp),
            strokeWidth = 5.dp
        )
    }
}

@Composable
fun ItemLoading(){
    Box(
        contentAlignment = Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .scale(0.5f)
                .size(50.dp)
        )
    }

}

@Composable
fun ErrorAction(
    error : String,
    onRetry : () -> Unit,
    modifier : Modifier = Modifier
){
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = error,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color.Red
        )
        OutlinedButton(onClick = {}) {
            Text(text = "Try again")
        }
    }
}