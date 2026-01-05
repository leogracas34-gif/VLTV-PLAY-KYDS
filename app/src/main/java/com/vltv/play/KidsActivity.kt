package com.vltv.play

import android.media.AudioManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.vltv.play.databinding.ActivityKidsBinding

class KidsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKidsBinding
    private val audioManager: AudioManager by lazy { 
        getSystemService(AUDIO_SERVICE) as AudioManager 
    }

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
            // 🎯 Foco inicial no primeiro card
            cardCultura.requestFocus()
            
            // ✨ EFEITO VISUAL TV + CELULAR (suave escala)
            listOf(cardCultura, cardDiscovery, cardCartoon, cardDisney, btnSairKids).forEach { view ->
                view.isFocusableInTouchMode = true
                
                view.setOnFocusChangeListener { _, hasFocus ->
                    view.animate()
                        .scaleX(if (hasFocus) 1.08f else 1.0f)
                        .scaleY(if (hasFocus) 1.08f else 1.0f)
                        .setDuration(200)
                        .start()
                        
                    // Som navegação TV ✅ Corrigido
                    if (hasFocus) {
                        playNavigationSound(SoundEffectConstants.NAVIGATION_UP)
                    }
                }
            }
            
            // D-Pad navigation 2x2 grid
            setupDpadPair(cardCultura, cardDiscovery)
            setupDpadPair(cardCartoon, cardDisney)
            setupVerticalNavigation()
            
            // D-Pad OK/Enter = Clique
            setupEnterKeyListener()
        }
    }
    
    /** ✅ Função auxiliar para som de navegação */
    private fun playNavigationSound(soundConstant: Int) {
        try {
            audioManager.playSoundEffect(soundConstant)
        } catch (e: Exception) {
            // Silencioso se não funcionar
        }
    }
    
    private fun setupDpadPair(leftCard: CardView, rightCard: CardView) {
        // ←→ Horizontal
        leftCard.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.action == KeyEvent.ACTION_DOWN) {
                rightCard.requestFocus()
                playNavigationSound(SoundEffectConstants.NAVIGATION_RIGHT)
                true
            } else null
        }
        
        rightCard.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.action == KeyEvent.ACTION_DOWN) {
                leftCard.requestFocus()
                playNavigationSound(SoundEffectConstants.NAVIGATION_LEFT)
                true
            } else null
        }
    }
    
    private fun setupVerticalNavigation() {
        binding.apply {
            // ↑↓ Vertical alinhado
            cardCultura.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN) {
                    cardCartoon.requestFocus()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_DOWN)
                    true
                } else null
            }
            
            cardDiscovery.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN) {
                    cardDisney.requestFocus()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_DOWN)
                    true
                } else null
            }
            
            cardCartoon.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN) {
                    cardCultura.requestFocus()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_UP)
                    true
                } else null
            }
            
            cardDisney.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN) {
                    cardDiscovery.requestFocus()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_UP)
                    true
                } else null
            }
        }
    }
    
    private fun setupEnterKeyListener() {
        // 🎮 OK/Enter clica no card focado
        listOf(binding.cardCultura, binding.cardDiscovery, binding.cardCartoon, binding.cardDisney).forEach { card ->
            card.setOnKeyListener { _, keyCode, event ->
                if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_BUTTON_A)
                    && event.action == KeyEvent.ACTION_DOWN) {
                    card.performClick()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
                    true
                } else null
            }
        }
        
        // Botão sair também
        binding.btnSairKids.setOnKeyListener { _, keyCode, event ->
            if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER)
                && event.action == KeyEvent.ACTION_DOWN) {
                binding.btnSairKids.performClick()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
                true
            } else null
        }
    }
    
    private fun setupClickListeners() {
        binding.apply {
            cardCultura.setOnClickListener { 
                Toast.makeText(this@KidsActivity, "📺 Abrindo Cultura Kids!", Toast.LENGTH_SHORT).show()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
                // TODO: Abrir player Cultura Kids
            }
            
            cardDiscovery.setOnClickListener { 
                Toast.makeText(this@KidsActivity, "🔬 Abrindo Discovery Kids!", Toast.LENGTH_SHORT).show()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
                // TODO: Abrir player Discovery Kids
            }
            
            cardCartoon.setOnClickListener { 
                Toast.makeText(this@KidsActivity, "🎨 Abrindo Cartoon Network!", Toast.LENGTH_SHORT).show()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
                // TODO: Abrir player Cartoon
            }
            
            cardDisney.setOnClickListener { 
                Toast.makeText(this@KidsActivity, "🦁 Abrindo Disney!", Toast.LENGTH_SHORT).show()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
                // TODO: Abrir player Disney
            }
            
            btnSairKids.setOnClickListener { 
                showPinDialog() 
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
            }
        }
    }
    
    private fun showPinDialog() {
        val pin = "1234"
        val input = EditText(this).apply { 
            inputType = InputType.TYPE_CLASS_NUMBER 
            setTextColor(0xFFFFFFFF.toInt())
            setBackgroundColor(0x80000000.toInt())
        }
        
        AlertDialog.Builder(this)
            .setTitle("🔐 PIN dos Pais")
            .setView(input)
            .setPositiveButton("OK") { _, _ ->
                if (input.text.toString() == pin) {
                    finish()
                    Toast.makeText(this, "✅ Saindo do modo Kids!", Toast.LENGTH_SHORT).show()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
                } else {
                    Toast.makeText(this, "❌ PIN incorreto!", Toast.LENGTH_SHORT).show()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_CANCEL)
                }
            }
            .setNegativeButton("Cancelar") { _, _ ->
                playNavigationSound(SoundEffectConstants.NAVIGATION_CANCEL)
            }
            .setCancelable(false)
            .show()
    }
    
    override fun onBackPressed() {
        showPinDialog()
    }
}
