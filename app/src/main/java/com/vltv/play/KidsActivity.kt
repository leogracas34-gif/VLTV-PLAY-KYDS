package com.vltv.play

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputType
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.widget.EditText
import com.vltv.play.databinding.ActivityKidsBinding

class KidsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKidsBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "👶 KidsActivity INICIADA!", Toast.LENGTH_SHORT).show()
        
        binding = ActivityKidsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupDpadNavigation()
        setupClickListeners()
    }
    
    private fun setupDpadNavigation() {
        with(binding) {
            // Foco inicial no primeiro card
            cardCultura.requestFocus()
            
            // Navegação D-Pad 2x2 grid
            setupDpadPair(cardCultura, cardDiscovery, true)
            setupDpadPair(cardCartoon, cardDisney, false)
            setupVerticalNavigation()
        }
    }
    
    private fun setupDpadPair(leftCard: CardView, rightCard: CardView, isTopRow: Boolean) {
        // Direita ←→ Esquerda
        leftCard.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.action == KeyEvent.ACTION_DOWN) {
                rightCard.requestFocus()
                true
            } else null
        }
        
        rightCard.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.action == KeyEvent.ACTION_DOWN) {
                leftCard.requestFocus()
                true
            } else null
        }
    }
    
    private fun setupVerticalNavigation() {
        binding.apply {
            // CIMA ←→ BAIXO (Linha 1 → Linha 2)
            cardCultura.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN) {
                    cardCartoon.requestFocus()
                    true
                } else null
            }
            
            cardDiscovery.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN) {
                    cardDisney.requestFocus()
                    true
                } else null
            }
            
            // BAIXO ←→ CIMA (Linha 2 → Linha 1)
            cardCartoon.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN) {
                    cardCultura.requestFocus()
                    true
                } else null
            }
            
            cardDisney.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN) {
                    cardDiscovery.requestFocus()
                    true
                } else null
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.apply {
            cardCultura.setOnClickListener { 
                Toast.makeText(this@KidsActivity, "📺 Abrindo Cultura Kids!", Toast.LENGTH_SHORT).show()
            }
            
            cardDiscovery.setOnClickListener { 
                Toast.makeText(this@KidsActivity, "🔬 Abrindo Discovery Kids!", Toast.LENGTH_SHORT).show()
            }
            
            cardCartoon.setOnClickListener { 
                Toast.makeText(this@KidsActivity, "🎨 Abrindo Cartoon Network!", Toast.LENGTH_SHORT).show()
            }
            
            cardDisney.setOnClickListener { 
                Toast.makeText(this@KidsActivity, "🦁 Abrindo Disney!", Toast.LENGTH_SHORT).show()
            }
            
            btnSairKids.setOnClickListener { showPinDialog() }
        }
    }
    
    private fun showPinDialog() {
        val pin = "1234"
        val input = EditText(this).apply { 
            inputType = InputType.TYPE_CLASS_NUMBER 
            setTextColor(0xFFFFFFFF.toInt())
        }
        
        AlertDialog.Builder(this)
            .setTitle("🔐 PIN dos Pais")
            .setView(input)
            .setPositiveButton("OK") { _, _ ->
                if (input.text.toString() == pin) {
                    finish()
                    Toast.makeText(this, "✅ Saindo do modo Kids!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "❌ PIN incorreto!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
    
    override fun onBackPressed() {
        showPinDialog()
    }
}
