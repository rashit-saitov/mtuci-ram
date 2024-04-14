package com.maasbodev.rickandmorty.domain.repository

import com.maasbodev.rickandmorty.data.local.LocalCharacterDataSource
import com.maasbodev.rickandmorty.domain.model.Character
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryTest {

    private val localCharacterDataSource: LocalCharacterDataSource = mockk(relaxed = true)
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        repository = CharacterRepository(localCharacterDataSource)
    }

    @Test
    fun `GIVEN a successful response WHEN findById THEN get the expected Character`() = runTest {
        val character = buildCharacter()
        coEvery { localCharacterDataSource.findById(ID) } returns character

        repository.findById(ID)

        coVerify { localCharacterDataSource.findById(ID) }
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
