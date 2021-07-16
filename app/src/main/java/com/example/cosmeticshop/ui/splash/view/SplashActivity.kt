package com.example.cosmeticshop.ui.splash.view


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.cosmeticshop.databinding.ActivitySplashBinding
import com.example.cosmeticshop.ui.productbrand.view.ProductBrandActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private var activitySplashBinding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding?.root)
        setUpSplash()
    }

    private fun setUpSplash() {

        val mTime = 3000L
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ProductBrandActivity::class.java)
            startActivity(intent)
            finish()
        }, mTime)

    }
}