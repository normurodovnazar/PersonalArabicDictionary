package com.bynazar.personalarabicdictionary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("original") val original: String,
    @ColumnInfo("arabic") val arabic: String
){
    constructor(original: String,arabic: String): this(0,original,arabic)
}
