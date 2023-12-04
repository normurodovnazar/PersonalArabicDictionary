package com.bynazar.personalarabicdictionary

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.bynazar.personalarabicdictionary.databinding.MainBinding
import java.util.Locale

class MainActivity : ComponentActivity(),TextToSpeech.OnInitListener {

    private lateinit var bind: MainBinding
    lateinit var tts: TextToSpeech
    private lateinit var db: AppDatabase
    private lateinit var dao: WordDao
    var list = listOf<Word>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = MainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        tts = TextToSpeech(this,this)
        db = Room.databaseBuilder(this,AppDatabase::class.java,"name").allowMainThreadQueries().build()
        dao = db.wordDao()
        list = dao.getAll().asReversed()
        updateList()

        bind.add.setOnClickListener {
            bind.main.visibility = View.GONE
            bind.adding.visibility = View.VISIBLE
        }
        bind.ok.setOnClickListener {
            if (bind.original.text.isNotEmpty() && bind.arabic.text.isNotEmpty()){
                dao.insertAll(Word(bind.original.text.toString(),bind.arabic.text.toString()))
                onBack()
                bind.original.text.clear()
                bind.arabic.text.clear()
                list = dao.getAll().asReversed()
                updateList()
            }
        }
        bind.search.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s!=null)
                    if (s.isNotEmpty()){
                        list = dao.findByOriginal(s.toString()).asReversed()
                        updateList()
                    }else {
                        list = dao.getAll().asReversed()
                        updateList()
                    }
            }
        })
    }

    private fun updateList() {
        val adapter = WordAdapter(
            list,
            object: OnClickWord{
                override fun onClick(word: Word, action: Action) {
                    if (action==Action.SPEAK) tts.speak(word.arabic,TextToSpeech.QUEUE_FLUSH,null,"")
                    else {
                        dao.delete(word)
                        list = dao.getAll().asReversed()
                        updateList()
                    }
                }
            }
        )
        bind.list.adapter = adapter
        bind.list.layoutManager = LinearLayoutManager(this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts.setLanguage(Locale.forLanguageTag("ar"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"Lang not supported",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (bind.adding.visibility==View.VISIBLE){
                onBack()
                return true
            }else if (bind.search.text.isNotEmpty()){
                bind.search.text.clear()
                list = dao.getAll()
                updateList()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun onBack() {
        bind.main.visibility = View.VISIBLE
        bind.adding.visibility = View.GONE
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}