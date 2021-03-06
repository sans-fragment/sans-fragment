package com.ezhemenski.sansfragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.OneShotPreDrawListener;

class TransitionManager {

    interface Container {
        @Nullable
        View getCurrentView();

        void addView(@NonNull View view);

        void removeView(@NonNull View view);
    }

    private final Container container;

    private ValueAnimator animator = null;

    TransitionManager(Container container) {
        this.container = container;
    }

    void putView(final @NonNull View newView, @Nullable Transition transition, final @NonNull Runnable onEnterTransitionEnd) {
        skip();
        final View oldView = container.getCurrentView();
        if (oldView == null) {
            container.addView(newView);
            OneShotPreDrawListener.add(newView, onEnterTransitionEnd);
            return;
        }
        if (transition == null) {
            container.removeView(oldView);
            container.addView(newView);
            OneShotPreDrawListener.add(newView, onEnterTransitionEnd);
            return;
        }
        final TransitionPerformer transitionPerformer = transition.create(oldView, newView);
        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                transitionPerformer.onFraction(animation.getAnimatedFraction());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                container.addView(newView);
                transitionPerformer.onStart();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                container.removeView(oldView);
                onEnterTransitionEnd.run();
                animator = null;
            }
        });
        animator.setDuration(transitionPerformer.duration());
        animator.start();
    }

    void skip() {
        if (animator != null) {
            animator.end();
        }
    }
}
