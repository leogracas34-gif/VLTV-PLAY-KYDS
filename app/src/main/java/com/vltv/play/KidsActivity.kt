package com.vltv.play

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class KidsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        Toast.makeText(this, "👶 KidsActivity INICIADA!", Toast.LENGTH_LONG).show()
        
        // Layout SEM binding (teste puro)
        val textView = TextView(this)
        textView.text = "KIDS FUNCIONOU! 🎉"
        textView.textSize = 30f
        textView.setTextColor(0xFFFFFFFF.toInt())
        textView.setPadding(50, 50, 50, 50)
        setContentView(textView)
        
        Toast.makeText(this, "Layout OK - sem crash!", Toast.LENGTH_LONG).show()
    }
}
