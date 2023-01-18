package com.joel.comics.utils

import com.joel.comics.R
import com.joel.comics.ui.presentation.views.destinations.CharactersScreenDestination
import com.joel.comics.ui.presentation.views.destinations.ComicsScreenDestination
import com.joel.comics.ui.presentation.views.destinations.Destination
import com.joel.comics.ui.presentation.views.destinations.SeriesScreenDestination

sealed class BottomBarRoutes(
    val title : String,
    val icon : Int,
    val destination : Destination
){

    object Comics : BottomBarRoutes(
        title = "Comics",
        icon = R.drawable.ic_baseline_comics_24,
        destination = ComicsScreenDestination

    )

    object Series : BottomBarRoutes(
        title = "Series",
        icon = R.drawable.ic_baseline_series_24,
        destination = SeriesScreenDestination

    )

    object Characters : BottomBarRoutes(
        title = "Characters",
        icon = R.drawable.ic_baseline_characters_24,
        destination = CharactersScreenDestination
    )

}
