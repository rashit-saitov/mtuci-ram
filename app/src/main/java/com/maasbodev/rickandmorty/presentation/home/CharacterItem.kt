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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(
    character: Character,
    detailViewModel: DetailViewModel,
    navController: NavHostController,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    modifier: Modifier = Modifier,
) {
    val showDialog = remember { mutableStateOf(false) }
    val dialogMessage = remember { mutableStateOf("") }
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
                            dialogMessage.value = "Character is not dead"
                            showDialog.value = true
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
                            dialogMessage.value = "Character is not alive"
                            showDialog.value = true
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
                    if (showDialog.value) {
                        AlertDialog(
                            onDismissRequest = { showDialog.value = false },
                            title = { Text("Status Information") },
                            text = { Text(dialogMessage.value) },
                            confirmButton = {
                                Button(
                                    onClick = { showDialog.value = false }
                                ) {
                                    Text("OK")
                                }
                            },
                            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
                        )
                    }
                }
            }
        }
    }
}
