package com.rmsaitov.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.rmsaitov.rickandmorty.presentation.detail.DetailScreen
import com.rmsaitov.rickandmorty.presentation.detail.DetailViewModel
import com.rmsaitov.rickandmorty.presentation.home.HomeScreen
import com.rmsaitov.rickandmorty.presentation.home.HomeViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HOME_SCREEN,
    ) {
        composable(route = HOME_SCREEN) {
            HomeScreen(
                characters = homeViewModel.characterPagingFlow.collectAsLazyPagingItems(),
                detailViewModel = detailViewModel,
                navController = navController,
            )
        }
        composable(route = DETAIL_SCREEN) {
            DetailScreen(detailViewModel)
        }
    }
}

internal const val HOME_SCREEN = "home_screen"
internal const val DETAIL_SCREEN = "detail_screen"
internal const val FAIL_SCREEN = "fail_screen"
