package com.example.mtg_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mtg_api.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding

    lateinit var cardsAdapter: CardAdapter
    val cardList = mutableListOf<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchCard.setOnQueryTextListener(this)
        initRecylerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(1, 11, 1, "Acerca de")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            11 -> {
                val intent = Intent(this, AcercaDeActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecylerView(){
        cardsAdapter = CardAdapter(cardList)

        binding.rvCards.layoutManager = LinearLayoutManager(this)
        binding.rvCards.adapter = cardsAdapter
    }

    private fun showErrorDialog() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.magicthegathering.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIMtg::class.java).getCardByName("cards?name=$query&language=spanish").execute()

            val results = call.body() as CardsResponse?

            runOnUiThread {
                if(call.isSuccessful){
                    val cards : List<Card> = results?.cards?: emptyList()
                    cardList.clear()
                    cardList.addAll(cards)
                    cardsAdapter.notifyDataSetChanged()
                } else {
                    showErrorDialog()
                }
                hideKeyboard()
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.lowercase()?.let { searchByName(it) }
        return true;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}