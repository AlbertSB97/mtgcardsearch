package com.example.mtg_api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mtg_api.databinding.ItemCardDetailBinding
import com.squareup.picasso.Picasso

class CardDetailAdapter(val cards: List<CardTranslation>) : RecyclerView.Adapter<CardDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_card_detail, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cards[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        private val binding = ItemCardDetailBinding.bind(view)

        fun bind(card: CardTranslation) {
            Picasso.get().load(card.imageUrl).placeholder(R.drawable.img_placeholder).into(binding.cardDetailImg)
            binding.cardDetailName.setText(card.name)
            binding.cardDetailType.setText(card.type)
            binding.cardDetailText.setText(card.text)
        }
    }
}