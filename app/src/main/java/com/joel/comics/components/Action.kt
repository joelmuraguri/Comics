package com.joel.comics.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.joel.comics.R

@Composable
fun Action(){

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier

    ) {
       IconButton(onClick = { /*TODO*/ }) {
           Image(
               painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
               contentDescription = "more_vert",
               alignment = Alignment.TopEnd
           )
       }
    }
}

@Preview
@Composable
fun ActionPreview(){
    Action()
}