package com.vltv.play

import android.media.AudioManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo.TYPE_CLASS_NUMBER
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vltv.play.databinding.ActivityKidsBinding

class KidsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKidsBinding
    private val audioManager: AudioManager by lazy { 
        getSystemService(AUDIO_SERVICE) as AudioManager 
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "👶 Kids TV HUB INICIADO!", Toast.LENGTH_SHORT).show()
        
        binding = ActivityKidsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupKidsNavigation()  // ✅ NOVA NAVEGAÇÃO TV + TOUCH
        setupClickListeners()
        setupTabsAndPager()
    }
    
    private fun setupKidsNavigation() {
        with(binding) {
            // 🎯 Foco inicial nos TABS (TV)
            tabLayout.requestFocus()
            
            // ✨ TV FOCUS + CELULAR TOUCH - Header, Tabs, Games, Sair
            val mainFocusables = listOf<View>(
                tabLayout,
                gamesSection,
                btnSairKids
            )
            
            mainFocusables.forEach { view ->
                view.isFocusable = true
                view.isFocusableInTouchMode = true
                
                view.setOnFocusChangeListener { _, hasFocus ->
                    // ✨ Animação TV Focus + Glow
                    view.animate()
                        .scaleX(if(hasFocus) 1.08f else 1.0f)
                        .scaleY(if(hasFocus) 1.08f else 1.0f)
                        .setDuration(200)
                        .start()
                    
                    if(hasFocus) {
                        playNavigationSound(SoundEffectConstants.NAVIGATION_UP)
                    }
                }
            }
            
            // 🎮 Jogos individuais - TV Focus + Touch
            listOf(
                btnMemoryGame, btnColoring, btnPuzzle, btnNumbers
            ).forEach { gameBtn ->
                gameBtn.isFocusable = true
                gameBtn.isFocusableInTouchMode = true
                
                gameBtn.setOnFocusChangeListener { _, hasFocus ->
                    gameBtn.animate()
                        .scaleX(if(hasFocus) 1.15f else 1.0f)
                        .scaleY(if(hasFocus) 1.15f else 1.0f)
                        .setDuration(150)
                        .start()
                    
                    if(hasFocus) {
                        playNavigationSound(SoundEffectConstants.NAVIGATION_RIGHT)
                    }
                }
            }
            
            // 🕹️ D-Pad entre seções principais (TV)
            setupMainNavigation()
        }
    }
    
    private fun setupMainNavigation() {
        with(binding) {
            // Tabs → Games → Sair (Vertical TV)
            tabLayout.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN) {
                    gamesSection.requestFocus()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_DOWN)
                    true
                } else false
            }
            
            gamesSection.setOnKeyListener { _, keyCode, event ->
                when {
                    keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN -> {
                        tabLayout.requestFocus()
                        playNavigationSound(SoundEffectConstants.NAVIGATION_UP)
                        true
                    }
                    keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN -> {
                        btnSairKids.requestFocus()
                        playNavigationSound(SoundEffectConstants.NAVIGATION_DOWN)
                        true
                    }
                    else -> false
                }
            }
            
            btnSairKids.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN) {
                    gamesSection.requestFocus()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_UP)
                    true
                } else false
            }
        }
    }
    
    private fun setupTabsAndPager() {
        // Tabs: Canais | Filmes | Séries
        val tabTitles = listOf("📺 CANAIS", "🎬 FILMES", "📺 SÉRIES")
        
        // Adapter do ViewPager (conteúdo kids filtrado)
        binding.viewPager.adapter = KidsContentPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
            tab.icon = null
        }.attach()
        
        // ViewPager também navegável TV
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                playNavigationSound(SoundEffectConstants.NAVIGATION_RIGHT)
            }
        })
    }
    
    private fun playNavigationSound(soundConstant: Int) {
        try {
            audioManager.playSoundEffect(soundConstant)
        } catch (e: Exception) {}
    }
    
    private fun setupClickListeners() {
        binding.apply {
            // 🎮 Jogos - TV Enter + Celular Touch
            btnMemoryGame.setOnClickListener { 
                startMemoryGame()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
            }
            btnColoring.setOnClickListener { 
                startColoringGame()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
            }
            btnPuzzle.setOnClickListener { 
                startPuzzleGame()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
            }
            btnNumbers.setOnClickListener { 
                startNumbersGame()
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
            }
            
            // Sair
            btnSairKids.setOnClickListener { 
                showPinDialog() 
                playNavigationSound(SoundEffectConstants.NAVIGATION_ACCEPT)
            }
        }
    }
    
    // 🎮 Mini Jogos Simples
    private fun startMemoryGame() {
        Toast.makeText(this, "🧠 Jogo da Memória (Em breve!)", Toast.LENGTH_LONG).show()
        // TODO: Dialog com grid 4x4 emojis
    }
    
    private fun startColoringGame() {
        Toast.makeText(this, "🎨 Pintar Desenhos (Em breve!)", Toast.LENGTH_LONG).show()
        // TODO: Color picker + desenho
    }
    
    private fun startPuzzleGame() {
        Toast.makeText(this, "🧩 Quebra-cabeça (Em breve!)", Toast.LENGTH_LONG).show()
        // TODO: Puzzle 3x3 imagem
    }
    
    private fun startNumbersGame() {
        Toast.makeText(this, "1️⃣ Contar Números (Em breve!)", Toast.LENGTH_LONG).show()
        // TODO: Sequência numérica
    }
    
    private fun showPinDialog() {
        val pin = "1234"
        val input = EditText(this).apply { 
            inputType = TYPE_CLASS_NUMBER
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
                } else {
                    Toast.makeText(this, "❌ PIN incorreto!", Toast.LENGTH_SHORT).show()
                    playNavigationSound(SoundEffectConstants.NAVIGATION_CANCEL)
                }
            }
            .setNegativeButton("Cancelar", null)
            .setCancelable(false)
            .show()
    }
    
    override fun onBackPressed() {
        showPinDialog()
    }
}
