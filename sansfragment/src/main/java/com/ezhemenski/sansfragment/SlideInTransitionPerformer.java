package com.ezhemenski.sansfragment;

import android.view.View;

class SlideInTransitionPerformer extends TransitionPerformer {

    SlideInTransitionPerformer(View oldView, View newView) {
        super(oldView, newView);
    }

    @Override
    public void onStart() {
        newView.setTranslationY(newView.getContext().getResources().getDisplayMetrics().heightPixels);
    }

    @Override
    public void onFraction(float fraction) {
        int h = newView.getHeight();
        if (h == 0) {
            if (fraction == 1f) {
                newView.setTranslationY(0f);
            }
            return;
        }
        newView.setTranslationY((1f - fraction) * (float) h);
    }
}
