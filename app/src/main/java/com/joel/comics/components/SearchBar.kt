package com.joel.comics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    hint : String = "",
    onSearch : (String) -> Unit

){
    var value by remember {
        mutableStateOf("")
    }

    val isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }


        OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        maxLines = 1,
        placeholder = {
            Text(
                text = "Search for a character",
                color = Color.LightGray
            )
        },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                backgroundColor = Color.White,
            )

    )

    if (isHintDisplayed){
        Text(
            text = hint,
            modifier = Modifier.padding(
                horizontal = 20.dp, vertical = 12.dp
            ),
            color = Color.LightGray
        )
    }
}
//
//@Preview
//@Composable
//fun SearchBarPreview(){
//    SearchBar("Search for a character")
//}