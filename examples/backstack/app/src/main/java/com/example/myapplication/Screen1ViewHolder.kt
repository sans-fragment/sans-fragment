package com.example.myapplication

import android.widget.Button
import com.ezhemenski.sansfragment.Navigator
import com.ezhemenski.sansfragment.Transitions
import com.ezhemenski.sansfragment.ViewHolder
import com.ezhemenski.sansfragment.ViewStackFrameLayout

class Screen1ViewHolder(container: ViewStackFrameLayout, navigator: Navigator<Screen>) :
    ViewHolder(container, R.layout.view_screen_1) {
    val button: Button = rootView.findViewById(R.id.button)

    init {
        button.setOnClickListener {
            navigator.push(Screen.SECOND, Transitions.SLIDE_IN)
        }
    }
}