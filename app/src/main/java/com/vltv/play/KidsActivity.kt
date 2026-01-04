package com.vltv.play

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vltv.play.databinding.ActivityHomeBinding

class KidsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.tvBannerTitle.text = "👶 KIDS OK!"
        Toast.makeText(this, "KidsActivity funcionando!", Toast.LENGTH_LONG).show()
    }
}
