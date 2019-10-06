package com.ezhemenski.sansfragment;

import android.support.v4.view.ViewCompat;
import android.view.View;

class SlideOutTransitionPerformer extends TransitionPerformer {

    SlideOutTransitionPerformer(View oldView, View newView) {
        super(oldView, newView);

        ViewCompat.setZ(oldView, ViewCompat.getZ(newView) + 1);
    }

    @Override
    public void onFraction(float fraction) {
        oldView.setTranslationY(fraction * (float) oldView.getHeight());
    }
}
