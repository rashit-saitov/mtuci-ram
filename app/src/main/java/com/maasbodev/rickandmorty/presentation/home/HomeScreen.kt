package com.maasbodev.rickandmorty.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.maasbodev.rickandmorty.domain.model.Character
import com.maasbodev.rickandmorty.presentation.detail.DetailViewModel
import com.maasbodev.rickandmorty.presentation.theme.Spacing
import com.maasbodev.rickandmorty.presentation.theme.Spacing.s8

@Composable
fun HomeScreen(
    characters: LazyPagingItems<Character>,
    detailViewModel: DetailViewModel,
    navController: NavHostController,
) {

    Box(modifier = Modifier.fillMaxSize()) {
        when (characters.loadState.refresh) {
            is LoadState.Error -> Toast.makeText(
                LocalContext.current,
                "Error: " + (characters.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()

            LoadState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )

            else -> {
                val searchTextState = remember { mutableStateOf(TextFieldValue("")) }
                Column(
                    modifier = Modifier.padding(s8)
                ) {
                    SearchView(searchTextState)
                    Spacer(modifier = Modifier.height(Spacing.s16))
                    ItemList(
                        detailViewModel = detailViewModel,
                        navController = navController,
                        state = searchTextState,
                        characterPagingItems = characters,
                    )
                }
            }
        }
    }
}
