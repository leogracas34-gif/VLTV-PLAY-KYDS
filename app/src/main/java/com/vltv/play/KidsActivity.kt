package com.vltv.play

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vltv.play.databinding.ActivityHomeBinding

class KidsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            val binding = ActivityHomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.tvBannerTitle.text = "👶 KIDS FUNCIONOU!"
            Toast.makeText(this, "KidsActivity OK - sem crash!", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "ERRO: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
