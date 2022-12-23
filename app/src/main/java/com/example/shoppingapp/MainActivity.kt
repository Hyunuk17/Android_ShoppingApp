package com.example.shoppingapp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.ui.login.Login
import com.example.shoppingapp.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        navView.setupWithNavController(navController) // nav 작동

        // Splash 효과
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->

                val animScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 2f)
                val animScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 2f)
                val animAlpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f)

                ObjectAnimator.ofPropertyValuesHolder(splashScreenView.iconView, animAlpha, animScaleX, animScaleY).run {
                    interpolator = AnticipateInterpolator()
                    duration = 800L
                    doOnEnd { splashScreenView.remove() }
                    start()
                }

            }
        }

        binding.login.setOnClickListener {
            if(Login.login == null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else {
                Login.login = null
                Toast.makeText(this@MainActivity, "Complete logout", Toast.LENGTH_LONG).show()

            }

        }

    }
}
