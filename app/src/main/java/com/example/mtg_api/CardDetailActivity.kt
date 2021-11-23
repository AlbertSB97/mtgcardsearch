package com.example.mtg_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.mtg_api.databinding.ActivityCardDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CardDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardDetailBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCardDetailBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cardTranslations = intent.getSerializableExtra("cardTranslations") as ArrayList<CardTranslation>

        tabLayout = binding.tabs
        viewPager = binding.viewPager

        viewPager.adapter = CardDetailAdapter(cardTranslations)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = cardTranslations[position].language
        }.attach()
    }
}