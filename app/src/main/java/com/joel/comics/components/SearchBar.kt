package com.joel.comics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun SearchBar(
    hint : String,
    onSearch : (String) -> Unit

){
    var text by remember {
        mutableStateOf("")
    }

    val isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
                            },
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .shadow(5.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.White, CircleShape)
                .height(50.dp)
                .fillMaxWidth()

        )

        if (isHintDisplayed){
            Text(
                text = hint,
                modifier = Modifier
                    .padding(20.dp, vertical = 12.dp),
                color = Color.LightGray,
                textAlign = TextAlign.Start

            )
        }
    }
}
//
//@Preview
//@Composable
//fun SearchBarPreview(){
//    SearchBar("Search for a character")
//}