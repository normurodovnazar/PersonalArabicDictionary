package com.bynazar.personalarabicdictionary

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT * FROM Word")
    fun getAll(): List<Word>

    @Query("SELECT * FROM Word WHERE original LIKE :original")
    fun findByName(original: String): Word

    @Insert
    fun insertAll(vararg words: Word)

    @Delete
    fun delete(word: Word)
}