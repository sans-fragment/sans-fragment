package com.ezhemenski.sansfragment;

import android.view.View;

import androidx.annotation.NonNull;

public class Transitions {
    public static final Transition FADE = new Transition() {
        @NonNull
        @Override
        public TransitionPerformer create(@NonNull View oldView, @NonNull View newView) {
            return new FadeTransitionPerformer(oldView, newView);
        }
    };

    public static final Transition SLIDE_IN = new Transition() {
        @NonNull
        @Override
        public TransitionPerformer create(@NonNull View oldView, @NonNull View newView) {
            return new SlideInTransitionPerformer(oldView, newView);
        }
    };

    public static final Transition SLIDE_OUT = new Transition() {
        @NonNull
        @Override
        public TransitionPerformer create(@NonNull View oldView, @NonNull View newView) {
            return new SlideOutTransitionPerformer(oldView, newView);
        }
    };
}
