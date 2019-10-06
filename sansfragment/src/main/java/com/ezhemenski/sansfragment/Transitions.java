package com.ezhemenski.sansfragment;

import android.support.annotation.NonNull;
import android.view.View;

public class Transitions {
    public static final Transition FADE = new Transition() {
        @NonNull
        @Override
        public TransitionPerformer create(@NonNull View oldView, @NonNull View newView) {
            return new FadeTransitionPerformer(oldView, newView);
        }
    };
}
