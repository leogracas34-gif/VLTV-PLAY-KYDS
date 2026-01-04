package com.vltv.play

import android.os.Bundle

class KidsActivity : VodActivity() {
    override fun getTitle(): String = "👶 Kids"
    
    override fun getApiUrl(): String {
        return "https://api.themoviedb.org/3/discover/movie?" +
               "api_key=9b73f5dd15b8165b1b57419be2f29128&" +
               "language=pt-BR&" +
               "with_genres=16,10751&" +  // Animation(16) + Family(10751)
               "sort_by=popularity.desc"
    }
}
