package app.bet.livescores.football.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import app.bet.livescores.football.ConstVal
import app.bet.livescores.football.R
import app.bet.livescores.football.activity.presenter.WebActivityPresenter
import app.bet.livescores.football.activity.view.WebActivityView
import im.delight.android.webview.AdvancedWebView
import kotlinx.android.synthetic.main.web_activity.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class WebActivity : MvpAppCompatActivity(), WebActivityView, AdvancedWebView.Listener {
    @InjectPresenter
    lateinit var presenter: WebActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity)
        webview.setCookiesEnabled(true)
        webview.settings.javaScriptEnabled = true
        webview.settings.domStorageEnabled = true
        webview.settings.allowContentAccess = true
        webview.setListener(this, this)
        if (savedInstanceState != null) {
            webview.restoreState(savedInstanceState)
        } else if (intent != null) {
            webview.loadUrl(intent.getStringExtra(ConstVal.REGION))
        }
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        webview.saveState(outState)

    }


    override fun onBackPressed() {
        if (!webview.onBackPressed()) {
            webview.onBackPressed()
            return
        }
        super.onBackPressed()
    }

    override fun onPageFinished(url: String?) {

    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {

    }

    override fun onExternalPageRequest(url: String?) {

    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {

    }


}