package com.bynazar.personalarabicdictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bynazar.personalarabicdictionary.databinding.WordBinding

class WordAdapter(private val list: List<Word>): RecyclerView.Adapter<WordAdapter.MViewHolder>() {
    inner class MViewHolder(private val bind: WordBinding): RecyclerView.ViewHolder(bind.root) {
        fun doSomething(){
            bind.delete.setOnClickListener {

            }
            bind.speak.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val bind = WordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MViewHolder(bind)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.doSomething()
    }
}