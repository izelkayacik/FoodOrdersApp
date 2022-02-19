package com.example.foodordersbootcamp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.foodordersbootcamp.databinding.ActivitySplashScreenBinding
import java.lang.Exception

class SplashScreen : AppCompatActivity() {

    private lateinit var tasarim: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        SplashScreenAnimation()
        SplashScreenAnimationThread()

    }

    fun SplashScreenAnimation() {
        val splashImageAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_image_animation)
        val splashTextMyAnimation =
            AnimationUtils.loadAnimation(this, R.anim.splash_text_my_animation)
        val splashTextDesireAnimation =
            AnimationUtils.loadAnimation(this, R.anim.splash_text_izindays_animation)

        tasarim.splashScreenImageView.startAnimation(splashImageAnimation)
        tasarim.splashTextMy.startAnimation(splashTextMyAnimation)
        tasarim.splashTextDesire.startAnimation(splashTextDesireAnimation)
    }

    fun SplashScreenAnimationThread() {
        //Splash Screen ekranı için bir Thread oluşturuyoruz.
        val background = object : Thread() {
            override fun run() {
                try {
                    //thread 5sn yani 5000ms uyusun
                    Thread.sleep(2000)
                    //intent ile splash ekranından sonra MainActivity ekranı açılsın diyoruz.
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}