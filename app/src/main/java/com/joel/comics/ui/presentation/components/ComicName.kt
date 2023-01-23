package com.joel.comics.ui.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComicName(
    comicName: String,
    ) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {

            Text(
                modifier = Modifier,
//                    .fillMaxWidth(0.83f),
                text = comicName,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )


    }
}