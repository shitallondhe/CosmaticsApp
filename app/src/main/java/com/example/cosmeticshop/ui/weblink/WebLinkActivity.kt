package com.example.cosmeticshop.ui.weblink

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.cosmeticshop.databinding.ActivityWebLinkBinding
import com.example.cosmeticshop.utils.Constants.Companion.WEB_LINK
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebLinkActivity : AppCompatActivity() {
    var binding: ActivityWebLinkBinding? = null
    private var weblink: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebLinkBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.progressBar?.visibility = View.VISIBLE
        weblink = intent.getStringExtra(WEB_LINK)
        openWebView(weblink)

    }

    private fun openWebView(weblink: String?) {

        if (weblink == null || weblink.isEmpty()) finish()

        binding?.webView?.webViewClient = MyWebViewClient()
        binding?.webView?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        binding?.webView?.settings?.setJavaScriptEnabled(true)
        weblink?.let { binding?.webView?.loadUrl(it) }

    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            with(view) { loadUrl(url) }
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding?.progressBar?.visibility = View.GONE
        }
    }
}



