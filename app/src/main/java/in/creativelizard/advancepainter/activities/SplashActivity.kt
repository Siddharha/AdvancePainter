package `in`.creativelizard.advancepainter.activities

import `in`.creativelizard.advancepainter.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        loadSplash()
    }

    private fun loadSplash() {
        Handler().postDelayed({
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        },1000)
    }
}
