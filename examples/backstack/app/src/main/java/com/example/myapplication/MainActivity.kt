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

        screenNavigator.set(getStack(savedInstanceState))
    }

    override fun onBackPressed() {
        if (screenNavigator.goBack(Transitions.SLIDE_OUT)) {
            return
        }
        finish()
    }

    //For saving stack in "saved state"
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putIntegerArrayList(
            STACK_KEY,
            ArrayList(screenNavigator.stackSnapshot.map { it.ordinal })
        )
    }

    //for restoring saved stack
    private fun getStack(savedInstanceState: Bundle?): List<Screen> =
        savedInstanceState?.getIntegerArrayList(STACK_KEY)
            ?.map { Screen.values()[it] }
            ?: listOf(Screen.FIRST)

    companion object {
        const val STACK_KEY = "MainActivity.stack"
    }
}
