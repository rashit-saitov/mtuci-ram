package com.maasbodev.rickandmorty.presentation

import arrow.core.left
import arrow.core.right
import com.maasbodev.rickandmorty.domain.model.Character
import com.maasbodev.rickandmorty.domain.repository.CharacterRepository
import com.maasbodev.rickandmorty.presentation.detail.DetailViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private val characterRepository: CharacterRepository = mockk(relaxed = true)
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailViewModel(
            characterRepository,
            testDispatcher,
        )
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a success character WHEN findById THEN get the expected result`() = runTest {
        val character = buildCharacter()
        val id = 1

        coEvery { characterRepository.findById(id) } returns character.right()
        viewModel.findById(id)
        coVerify { characterRepository.findById(id) }
    }

    @Test
    fun `GIVEN a failure character WHEN findById THEN get the expected result`() = runTest {
        val id = 1

        coEvery { characterRepository.findById(id) } returns Throwable().left()
        viewModel.findById(id)
        coVerify { characterRepository.findById(id) }
    }

    private fun buildCharacter() = Character(
        id = ID,
        name = NAME,
        status = STATUS,
        species = SPECIES,
        type = TYPE,
        gender = GENDER,
        originName = ORIGINNAME,
        originUrl = ORIGINURL,
        locationName = LOCATIONNAME,
        locationUrl = LOCATIONURL,
        image = IMAGE,
        episode = EPISODE,
        url = URL,
        created = CREATED,
    )
}

private const val ID = 1
private const val NAME = "A Name"
private const val STATUS = "A Status"
private const val SPECIES = "An Species"
private const val TYPE = "A Type"
private const val GENDER = "A Gender"
private const val ORIGINNAME = "An Origin Name"
private const val ORIGINURL = "An Origin Url"
private const val LOCATIONNAME = "A Location Name"
private const val LOCATIONURL = "A Location Url"
private const val IMAGE = "An Image"
private val EPISODE = emptyList<String>()
private const val URL = "A Url"
private const val CREATED = "A Created Timestamp"
