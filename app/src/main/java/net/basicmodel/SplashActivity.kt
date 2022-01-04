package net.basicmodel

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mylhyl.acp.Acp
import com.mylhyl.acp.AcpListener
import com.mylhyl.acp.AcpOptions
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Glide.with(this)
            .asGif()
            .load(R.mipmap.splash)
            .into(image)
        Acp.getInstance(this).request(AcpOptions.Builder().setPermissions(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
        ).build(), object : AcpListener {
            override fun onGranted() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

            override fun onDenied(permissions: MutableList<String>?) {
                finish()
            }

        })
    }
}