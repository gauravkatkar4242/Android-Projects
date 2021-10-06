package com.example.getrequest

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterDAO {

    @Insert
    suspend fun insertCharacter(character : Data)

    @Update
    suspend fun updateCharacter(character : Data)

    @Delete
    suspend fun deleteCharacter(character : Data)

    @Query("SELECT * FROM character")
    fun getCharacter(): LiveData<List<Data>>

}