package com.bynazar.personalarabicdictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.room.Room
import com.bynazar.personalarabicdictionary.databinding.MainBinding

class MainActivity : ComponentActivity() {

    lateinit var bind: MainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = MainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val db = Room.databaseBuilder(this,AppDatabase::class.java,"name").build()
        val wordDao: WordDao = db.wordDao()

    }
}