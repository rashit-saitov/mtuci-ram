package com.rmsaitov.rickandmorty.presentation.navigation

import androidx.navigation.NavController

val navToDetail: (NavController, String) -> Unit =
    { navController, destination -> navController.navigate(destination) }
