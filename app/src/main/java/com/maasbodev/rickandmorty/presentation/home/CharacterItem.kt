package com.maasbodev.rickandmorty.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.maasbodev.rickandmorty.domain.model.Character
import com.maasbodev.rickandmorty.presentation.detail.DetailViewModel
import com.maasbodev.rickandmorty.presentation.navigation.DETAIL_SCREEN
import com.maasbodev.rickandmorty.presentation.navigation.navToDetail
import com.maasbodev.rickandmorty.presentation.theme.Spacing.s16
import com.maasbodev.rickandmorty.presentation.theme.Spacing.s4
import com.maasbodev.rickandmorty.presentation.theme.Spacing.s8
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(
    character: Character,
    detailViewModel: DetailViewModel,
    navController: NavHostController,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(s4),
        shape = RoundedCornerShape(s4),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .fillMaxHeight(),
            )
            Spacer(modifier = Modifier.width(s16))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(s8))
                Text(
                    text = "Last known location:",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(s4))
                Text(
                    text = character.locationName,
                    style = MaterialTheme.typography.labelLarge,
                )
                Spacer(modifier = Modifier.height(s8))
                Row {
                    Button(
                        onClick = {
                        if (character.status == "Dead") {
                            detailViewModel.findById(character.id)
                            navToDetail(navController, DETAIL_SCREEN)
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Character is not dead",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    },
                        modifier = Modifier
                            .padding(8.dp)
                            .shadow(4.dp, shape = RoundedCornerShape(10.dp))
                            .background(Color.Red, shape = RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text("Dead", fontSize = 25.sp)
                    }
                    Button(
                        onClick = {
                        if (character.status == "Alive") {
                            detailViewModel.findById(character.id)
                            navToDetail(navController, DETAIL_SCREEN)
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Character is not alive",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    },
                        modifier = Modifier
                            .padding(8.dp)
                            .shadow(4.dp, shape = RoundedCornerShape(10.dp))
                            .background(Color.Green, shape = RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                    ) {
                        Text("Alive", fontSize = 25.sp)
                    }
                }
            }
        }
    }
}
