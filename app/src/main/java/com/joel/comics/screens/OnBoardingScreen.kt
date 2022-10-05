package com.joel.comics.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.joel.comics.R
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator
){

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {


            Spacer(modifier = Modifier.width(20.dp))

            Card(
                elevation = 5.dp,
                modifier = Modifier
                    .clickable {

                    },
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.marvel),
                    contentDescription = "marvel image",
                    modifier = Modifier
                        .size(120.dp)
                        .fillMaxWidth()
                )
            }

    }
}



