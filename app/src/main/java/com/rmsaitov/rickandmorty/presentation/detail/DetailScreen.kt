package com.rmsaitov.rickandmorty.presentation.detail

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.rmsaitov.rickandmorty.R
import com.rmsaitov.rickandmorty.presentation.theme.FontSize.f16
import com.rmsaitov.rickandmorty.presentation.theme.FontSize.f32
import com.rmsaitov.rickandmorty.presentation.theme.Spacing.s16
import com.rmsaitov.rickandmorty.presentation.theme.Spacing.s8

@Composable
fun DetailScreen(detailViewModel: DetailViewModel) {

    when (val viewState = detailViewModel.viewState) {
        is DetailViewModel.ViewState.Error -> DetailScreenLoadingError(true)
        DetailViewModel.ViewState.Loading -> DetailScreenLoadingError(false)
        is DetailViewModel.ViewState.Success -> Scaffold { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .testTag(COLUMN_TAG),
            ) {
                AsyncImage(
                    model = viewState.character.image,
                    contentDescription = viewState.character.name,
                    modifier = Modifier
                        .aspectRatio(1f),
                )
                Text(
                    modifier = Modifier.padding(start = s8),
                    text = viewState.character.name,
                    fontSize = f32,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = s8),
                    text = "${viewState.character.status} - ${viewState.character.species}",
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(s16))
                Text(
                    modifier = Modifier.padding(start = s8),
                    text = "Type: ${viewState.character.type.ifEmpty { "?" }}",
                    fontSize = f16,
                )
                Text(
                    modifier = Modifier.padding(start = s8),
                    text = "Gender: ${viewState.character.gender}",
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(s16))
                Text(
                    modifier = Modifier.padding(start = s8),
                    text = "Known origin:",
                    fontSize = f16,
                    color = Color.Gray,
                )
                Text(
                    modifier = Modifier.padding(start = s8),
                    text = viewState.character.originName,
                    fontSize = f16,
                )
                Spacer(modifier = Modifier.height(s16))
                Text(
                    modifier = Modifier.padding(start = s8),
                    text = "Last known location:",
                    fontSize = f16,
                    color = Color.Gray,
                )
                Text(
                    modifier = Modifier.padding(start = s8),
                    text = viewState.character.locationName,
                    fontSize = f16,
                )
                Spacer(modifier = Modifier.height(s16))
                Spacer(modifier = Modifier.height(s16))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.right_answer),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(alignment = Alignment.Center),
                        fontSize = f32,
                        color = Color.Green,
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreenLoadingError(error: Boolean) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (error) {
            Text(
                text = stringResource(id = R.string.download_error),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(alignment = Alignment.Center),
                fontSize = f32
            )
        } else {
            CircularProgressIndicator()
        }
    }
}

@VisibleForTesting
internal const val COLUMN_TAG = "COLUMN_TAG"
