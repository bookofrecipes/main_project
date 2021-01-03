package ru.geekbrains.bookofrecipes.service.androidtools

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi

@RequiresApi(api = Build.VERSION_CODES.O)
fun vibrate(vibrator: Vibrator) {
    val effect =
            VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
    if (vibrator.hasVibrator()) {
        vibrator.vibrate(effect)
    }
}