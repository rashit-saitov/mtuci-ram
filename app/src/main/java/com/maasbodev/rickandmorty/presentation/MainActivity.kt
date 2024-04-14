package com.maasbodev.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.maasbodev.rickandmorty.presentation.detail.DetailViewModel
import com.maasbodev.rickandmorty.presentation.home.HomeViewModel
import com.maasbodev.rickandmorty.presentation.navigation.NavGraph
import com.maasbodev.rickandmorty.presentation.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickAndMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val navController = rememberNavController()
                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    val detailViewModel = hiltViewModel<DetailViewModel>()
                    NavGraph(
                        navController = navController,
                        homeViewModel = homeViewModel,
                        detailViewModel = detailViewModel
                    )
                }
            }
        }
    }
}
