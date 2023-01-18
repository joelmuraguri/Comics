package com.joel.comics.core

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.joel.comics.ui.theme.bottomColor
import com.joel.comics.utils.BottomBarRoutes
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import java.time.format.TextStyle
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import com.joel.comics.R


@Composable
fun CustomScaffold(
    navController: NavController,
    showBottomBar : Boolean = true,
    showTopBar : Boolean = true,
    items : List<BottomBarRoutes> = listOf(
        BottomBarRoutes.Comics,
        BottomBarRoutes.Series,
        BottomBarRoutes.Characters
    ),
    content : @Composable (paddingValues : PaddingValues) -> Unit
){

    Scaffold(
        bottomBar = {
            if (showBottomBar){
                BottomNavigation(
                    elevation = 5.dp,
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            selected = currentDestination?.route?.contains(screen.destination.route) == true,
                            onClick = {
                                navController.navigate(screen.destination.route){
                                    navController.graph.startDestinationRoute?.let { bottom_route ->
                                        popUpTo(bottom_route){
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = screen.title)
                            },
                            selectedContentColor = bottomColor,
                            unselectedContentColor = Color.LightGray,
                            alwaysShowLabel = true,
                            label = {
                                Text(text = screen.title)
                            }
                        )
                    }
                }
            }
        },
//        topBar = {
//            val state = rememberCollapsingToolbarScaffoldState()
//            val textSize = (18 + ( 30-12 ) * state.toolbarState.progress).sp
//
//            CollapsingToolbarScaffold(
//                modifier = Modifier,
//                state = state,
//                scrollStrategy = ScrollStrategy.EnterAlways,
//                toolbar = {
//
//                    Box(
//                        modifier = Modifier
//                            .background(MaterialTheme.colors.background)
//                            .pin()
//                            .height(150.dp)
//                    )
//
//
//
//
//                    Image(
//                        painter = painterResource(id = R.drawable.marvel_one),
//                        contentDescription = "avengers",
//                        contentScale = ContentScale.Fit,
//                        alpha = if (textSize.value == 18f) 0f else 1f
//                    )
//
//                    Text(
//                        text = "Home of Marvel Heroes",
//                        modifier = Modifier
//                            .road(whenCollapsed = Alignment.TopStart, whenExpanded = Alignment.BottomCenter),
//                        style = androidx.compose.ui.text.TextStyle(
//                            color = Color.White,
//                            fontSize = textSize
//                        ),
//                    )
//                })
//            {
//
//            }
//
//        }
    ) { innerPadding ->
        content(innerPadding)
    }

}