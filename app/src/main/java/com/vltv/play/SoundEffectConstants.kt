package com.vltv.play

import android.media.AudioManager
import android.media.SoundEffectConstants

/**
 * Constantes de efeitos sonoros para navegação TV/Kids
 */
object SoundEffectConstants {
    const val NAVIGATION_UP = AudioManager.FX_KEYPRESS_STANDARD
    const val NAVIGATION_DOWN = AudioManager.FX_KEYPRESS_STANDARD
    const val NAVIGATION_LEFT = AudioManager.FX_KEYPRESS_STANDARD
    const val NAVIGATION_RIGHT = AudioManager.FX_KEYPRESS_STANDARD
    const val NAVIGATION_ACCEPT = AudioManager.FX_KEY_CLICK
    const val NAVIGATION_CANCEL = AudioManager.FX_FOCUS_NAVIGATION_UP
}
