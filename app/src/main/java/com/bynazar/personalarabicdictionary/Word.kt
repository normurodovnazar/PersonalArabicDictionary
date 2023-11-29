package com.bynazar.personalarabicdictionary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey val id: Int,
    @ColumnInfo("original") val original: String,
    @ColumnInfo("arabic") val arabic: String
)
