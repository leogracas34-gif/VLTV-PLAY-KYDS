package com.vltv.play

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.vltv.play.databinding.ActivityHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class KidsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHomeBinding
    private val TMDB_API_KEY = "9b73f5dd15b8165b1b57419be2f29128"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.tvBannerTitle.text = "👶 Kids TMDB..."  // ← CORRETO!
        carregarKidsTMDB()
    }
    
    private fun carregarKidsTMDB() {
        val urlKids = "https://api.themoviedb.org/3/discover/movie?" +
                     "api_key=$TMDB_API_KEY&language=pt-BR&" +
                     "with_genres=16,10751&sort_by=popularity.desc"
                     
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val jsonTxt = URL(urlKids).readText()
                val json = JSONObject(jsonTxt)
                val results = json.getJSONArray("results")
                
                if (results.length() > 0) {
                    val item = results.getJSONObject(0)
                    val titulo = item.optString("title", "Kids")
                    val backdrop = item.optString("backdrop_path", "")
                    
                    withContext(Dispatchers.Main) {
                        binding.tvBannerTitle.text = titulo  // ← HomeActivity ID!
                        binding.tvBannerOverview.text = "Conteúdo infantil!"  // ← HomeActivity ID!
                        
                        if (backdrop.isNotEmpty()) {
                            Glide.with(this@KidsActivity)
                                .load("https://image.tmdb.org/t/p/w1280$backdrop")
                                .into(binding.imgBanner)  // ← HomeActivity ID!
                        }
                    }
                }
            } catch (e: Exception) { }
        }
    }
}
