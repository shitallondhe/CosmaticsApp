package com.example.cosmaticshop.ui.weblink

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.cosmaticshop.databinding.ActivityWebLinkBinding
import com.example.cosmaticshop.utils.Constants.Companion.WEB_LINK


/**
 * WebLinkActivity
 */
class WebLinkActivity : AppCompatActivity() {
    var binding: ActivityWebLinkBinding? = null
    var weblink: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebLinkBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        weblink = intent.getStringExtra(WEB_LINK)
        openWebView(weblink)
    }

    /**
     *Open Weblink in webview
     **/
    private fun openWebView(weblink: String?) {

        if (weblink == null || weblink.isEmpty()) finish()

        binding?.webview?.webViewClient = MyWebViewClient()
        binding?.webview?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        binding?.webview?.settings?.javaScriptEnabled = true
        binding?.webview?.webViewClient = WebViewClient()
        weblink?.let { binding?.webview?.loadUrl(it) }

    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            with(view) { loadUrl(url) }
            return true
        }
    }
}


