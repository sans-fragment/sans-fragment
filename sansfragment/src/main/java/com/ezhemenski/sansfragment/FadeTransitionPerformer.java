package com.ezhemenski.sansfragment;

import android.view.View;

import androidx.annotation.NonNull;

class FadeTransitionPerformer extends TransitionPerformer {

    FadeTransitionPerformer(@NonNull View oldView, @NonNull View newView) {
        super(oldView, newView);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onFraction(float fraction) {
        oldView.setAlpha(1f - fraction);
        newView.setAlpha(fraction);
    }
}
