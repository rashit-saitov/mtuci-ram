package com.maasbodev.rickandmorty.local.data

import com.maasbodev.rickandmorty.data.local.CharacterDbModel
import com.maasbodev.rickandmorty.data.local.toCharacter
import com.maasbodev.rickandmorty.data.local.toCharacterDbModel
import com.maasbodev.rickandmorty.data.remote.model.CharacterDto
import com.maasbodev.rickandmorty.data.remote.model.OriginLocationObject
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharacterDbModelMapperTest {

    @Test
    fun `GIVEN a CharacterDto WHEN toCharacterDbModel THEN return the expected Character`() {
        val characterDto = buildCharacterDto()
        val originLocationObject = buildOriginLocationObject()
        val result = characterDto.toCharacterDbModel()

        assertEquals(result.id, ID)
        assertEquals(result.name, NAME)
        assertEquals(result.status, STATUS)
        assertEquals(result.species, SPECIES)
        assertEquals(result.type, TYPE)
        assertEquals(result.gender, GENDER)
        assertEquals(result.originName, originLocationObject.name)
        assertEquals(result.originUrl, originLocationObject.url)
        assertEquals(result.locationName, originLocationObject.name)
        assertEquals(result.locationUrl, originLocationObject.url)
        assertEquals(result.image, IMAGE)
        assertEquals(result.episode, EPISODE)
        assertEquals(result.url, URL)
        assertEquals(result.created, CREATED)
    }

    @Test
    fun `GIVEN a CharacterDbModel WHEN toCharacter THEN return the expected Character`() {
        val characterDbModel = buildCharacterDbModel()
        val originLocationObject = buildOriginLocationObject()
        val result = characterDbModel.toCharacter()

        assertEquals(result.id, ID)
        assertEquals(result.name, NAME)
        assertEquals(result.status, STATUS)
        assertEquals(result.species, SPECIES)
        assertEquals(result.type, TYPE)
        assertEquals(result.gender, GENDER)
        assertEquals(result.originName, originLocationObject.name)
        assertEquals(result.originUrl, originLocationObject.url)
        assertEquals(result.locationName, originLocationObject.name)
        assertEquals(result.locationUrl, originLocationObject.url)
        assertEquals(result.image, IMAGE)
        assertEquals(result.episode, EPISODE)
        assertEquals(result.url, URL)
        assertEquals(result.created, CREATED)
    }

    private fun buildCharacterDto() = CharacterDto(
        id = ID,
        name = NAME,
        status = STATUS,
        species = SPECIES,
        type = TYPE,
        gender = GENDER,
        origin = buildOriginLocationObject(),
        location = buildOriginLocationObject(),
        image = IMAGE,
        episode = EPISODE,
        url = URL,
        created = CREATED,
    )

    private fun buildCharacterDbModel() = CharacterDbModel(
        id = ID,
        name = NAME,
        status = STATUS,
        species = SPECIES,
        type = TYPE,
        gender = GENDER,
        originName = ORIGINLOCATIONNAME,
        originUrl = ORIGINLOCATIONURL,
        locationName = ORIGINLOCATIONNAME,
        locationUrl = ORIGINLOCATIONURL,
        image = IMAGE,
        episode = EPISODE,
        url = URL,
        created = CREATED,
    )

    private fun buildOriginLocationObject() = OriginLocationObject(
        name = ORIGINLOCATIONNAME,
        url = ORIGINLOCATIONURL,
    )
}

private const val ID = 1
private const val NAME = "A Name"
private const val STATUS = "A Status"
private const val SPECIES = "An Species"
private const val TYPE = "A Type"
private const val GENDER = "A Gender"
private const val IMAGE = "An Image"
private val EPISODE = emptyList<String>()
private const val URL = "A Url"
private const val CREATED = "A Created Timestamp"
private const val ORIGINLOCATIONNAME = "An Origin Name"
private const val ORIGINLOCATIONURL = "An Origin Url"
