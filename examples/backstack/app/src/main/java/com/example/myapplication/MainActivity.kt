package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ezhemenski.sansfragment.Navigator
import com.ezhemenski.sansfragment.Transitions
import com.ezhemenski.sansfragment.ViewStackFrameLayout

class MainActivity : AppCompatActivity() {

    private val screenContainer: ViewStackFrameLayout by lazy {
        findViewById<ViewStackFrameLayout>(R.id.screen_container)
    }

    private lateinit var screenNavigator: Navigator<Screen>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenNavigator = screenContainer.setAdapter { container, screen, navigator ->
            when (screen) {
                Screen.FIRST -> Screen1ViewHolder(container, navigator)
                Screen.SECOND -> Screen2ViewHolder(container, navigator)
            }
        }

        screenNavigator.set(Screen.FIRST)
    }

    override fun onBackPressed() {
        if (screenNavigator.goBack(Transitions.SLIDE_OUT)) {
            return
        }
        finish()
    }
}
