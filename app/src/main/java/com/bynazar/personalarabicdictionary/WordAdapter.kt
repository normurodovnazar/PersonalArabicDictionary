package com.bynazar.personalarabicdictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bynazar.personalarabicdictionary.databinding.WordBinding

class WordAdapter(private val list: List<Word>, private val onClickWord: OnClickWord): RecyclerView.Adapter<WordAdapter.MViewHolder>() {
    inner class MViewHolder(private val bind: WordBinding, private val onClickWord: OnClickWord): RecyclerView.ViewHolder(bind.root) {
        fun doSomething(position: Int){
            bind.original.text = list[position].original
            bind.arabic.text = list[position].arabic
            bind.delete.setOnClickListener {
                onClickWord.onClick(list[position],Action.DELETE)
            }
            bind.speak.setOnClickListener {
                onClickWord.onClick(list[position],Action.SPEAK)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val bind = WordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MViewHolder(bind,onClickWord)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.doSomething(position)
    }
}