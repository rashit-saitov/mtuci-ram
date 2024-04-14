package com.maasbodev.rickandmorty.data.local

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun restoreList(listOfString: String): List<String> {
        return listOfString.split(", ")
    }

    @TypeConverter
    fun saveList(listOfString: List<String>): String {
        return listOfString.joinToString(", ")
    }
}
