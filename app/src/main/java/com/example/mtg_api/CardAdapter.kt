package com.example.mtg_api

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mtg_api.databinding.ItemCardBinding
import com.squareup.picasso.Picasso

class CardAdapter(val cards: List<Card>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cards[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_card, parent, false))
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCardBinding.bind(view)

        fun bind(card: Card) {

            val cardEnglishTranslation = CardTranslation(
                card.name,
                card.text,
                card.type,
                card.image,
                "English"
            )

            Picasso.get().load(card.image).placeholder(R.drawable.img_placeholder).into(binding.cardImage)
            binding.cardName.setText(card.name)
            binding.cardManaCost.setText("Cost: " + card.manaCost)
            binding.cardSetName.setText("Set: " + card.set)
            binding.card.setOnClickListener { toDetailActivity(card.foreignNames, cardEnglishTranslation) }
        }

        private fun toDetailActivity(cardForeignNames: List<CardTranslation>, cardEnglishTranslation: CardTranslation){
            val cardTranslations = arrayListOf<CardTranslation>()
            cardTranslations.addAll(cardForeignNames)
            cardTranslations.add(0, cardEnglishTranslation)
            val intent = Intent(binding.root.context, CardDetailActivity::class.java).apply {
                putExtra("cardTranslations", cardTranslations)
            }

            binding.root.context.startActivity(intent)
        }
    }
}