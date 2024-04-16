package com.rmsaitov.rickandmorty.screens.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.rmsaitov.rickandmorty.domain.model.Character
import com.rmsaitov.rickandmorty.presentation.detail.COLUMN_TAG
import com.rmsaitov.rickandmorty.presentation.detail.DetailScreen
import com.rmsaitov.rickandmorty.presentation.detail.DetailViewModel
import com.rmsaitov.rickandmorty.presentation.theme.RickAndMortyTheme
import com.rmsaitov.rickandmorty.testing.relaxedMockk
import io.mockk.every
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailScreenTest() {
        val detailViewModel: DetailViewModel = relaxedMockk()
        every { detailViewModel.viewState } returns DetailViewModel.ViewState.Success(buildCharacter())

        composeTestRule.setContent {
            RickAndMortyTheme {
                DetailScreen(detailViewModel)
            }
        }
        composeTestRule.onNodeWithTag(COLUMN_TAG).assertIsDisplayed()
    }
}

private fun buildCharacter() = Character(
    id = 1,
    name = "A Name",
    status = "A Status",
    species = "A Species",
    type = "A Type",
    gender = "A Gender",
    originName = "An Origin Name",
    originUrl = "An Origin Url",
    locationName = "A Location Name",
    locationUrl = "A Location Url",
    image = "An Image",
    episode = emptyList(),
    url = "A Url",
    created = "A Created Timestamp",
)
