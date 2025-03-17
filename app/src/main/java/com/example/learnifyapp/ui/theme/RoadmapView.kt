package com.example.learnifyapp.ui.theme

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Roadmap(onBackPressed: () -> Unit) {
    val context = LocalContext.current
    val url = "https://roadmap.sh/roadmaps"

    val webView = remember { mutableStateOf<WebView?>(null) }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        // Load all links within the WebView
                        view?.loadUrl(request?.url.toString())
                        return true
                    }
                }
                settings.javaScriptEnabled = true // Enable JavaScript
                settings.domStorageEnabled = true // Enable DOM storage
                loadUrl(url)
                webView.value = this
            }
        },
        modifier = Modifier.fillMaxHeight()
    )

    // Handle back navigation
    BackHandler {
        if (webView.value?.canGoBack() == true) {
            webView.value?.goBack() // Go back in WebView history
        } else {
            onBackPressed() // Exit WebView screen
        }
    }

    // Clean up WebView when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            webView.value?.destroy() // Clean up WebView
        }
    }
}