package kz.almaty.satbayevuniversity.ui.references

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Base64.NO_PADDING

import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kz.almaty.satbayevuniversity.R
import kz.almaty.satbayevuniversity.databinding.ReferenseWebViewBinding
import kz.almaty.satbayevuniversity.ui.references.history.ReferencesFragment
import java.util.*

class ReferenseWebView: AppCompatActivity(){

    lateinit var binding: ReferenseWebViewBinding
    lateinit var webView: WebView

    companion object{
        fun newInstanse() = ReferenseWebView()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.referense_web_view)
        webView = binding.webView
        webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://sso.satbayev.university/account/login?ReturnUrl=https%3A%2F%2Fsso.satbayev.university%2F#!/")
    }
}