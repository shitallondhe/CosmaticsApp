package com.example.cosmaticshop.ui.splash.view


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.cosmaticshop.databinding.ActivitySplashBinding
import com.example.cosmaticshop.ui.main.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint


/**
 * Splash Screen
 */
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private var activitySplashBinding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding?.root)
        setUpSplash()

    }

    /**
     * Setups splash screen
     */
    private fun setUpSplash() {

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        val mTime = 3000L
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, mTime) // 3000 is the delayed time in milliseconds.

    }
}