package com.ezhemenski.sansfragment;

import android.support.annotation.NonNull;
import android.view.View;

class FadeTransitionPerformer extends TransitionPerformer {

    FadeTransitionPerformer(@NonNull View oldView, @NonNull View newView) {
        super(oldView, newView);
    }

    @Override
    public void onFraction(float fraction) {
        oldView.setAlpha(1f - fraction);
        newView.setAlpha(fraction);
    }
}
