package com.ezhemenski.sansfragment;

import android.view.View;

import androidx.core.view.ViewCompat;

class SlideOutTransitionPerformer extends TransitionPerformer {

    SlideOutTransitionPerformer(View oldView, View newView) {
        super(oldView, newView);
    }

    @Override
    public void onStart() {
        ViewCompat.setZ(oldView, ViewCompat.getZ(newView) + 1);
    }

    @Override
    public void onFraction(float fraction) {
        oldView.setTranslationY(fraction * (float) oldView.getHeight());
    }
}
