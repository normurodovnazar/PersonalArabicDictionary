package com.bynazar.personalarabicdictionary

interface OnClickWord {
    fun onClick(position: Int,word: Word,action: Action)
}