package com.ezhemenski.sansfragment;

import android.view.View;

import androidx.annotation.FloatRange;

@SuppressWarnings("WeakerAccess")
abstract public class TransitionPerformer {
    protected final View oldView;
    protected final View newView;

    public TransitionPerformer(View oldView, View newView) {
        this.oldView = oldView;
        this.newView = newView;
    }

    public abstract void onStart();

    public abstract void onFraction(@FloatRange(from = 0.0, to = 1.0) float fraction);

    public int duration() {
        return 300;
    }
}
