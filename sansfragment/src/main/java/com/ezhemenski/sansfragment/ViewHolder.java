package com.ezhemenski.sansfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ViewHolder implements TransitionTracker {

    protected final View rootView;

    private final List<Runnable> doOnTransitionEnd = new ArrayList<>();
    boolean isReadyToRender = false;
    private TransitionTracker parentTransitionTracker = null;

    @SuppressWarnings("WeakerAccess")
    public ViewHolder(@NonNull View rootView) {
        this.rootView = rootView;
        rootView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                onAttach();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                onDetach();
            }
        });
    }

    public ViewHolder(@NonNull ViewStackFrameLayout container, int layoutRes) {
        this(LayoutInflater.from(container.getContext()).inflate(layoutRes, container, false));
    }

    protected Context getContext() {
        return rootView.getContext();
    }

    @CallSuper
    protected void onAttach() {
    }

    @CallSuper
    protected void onDetach() {
        doOnTransitionEnd.clear();
        isReadyToRender = false;
    }

    void onEnterTransitionEnd() {
        for (Runnable task : doOnTransitionEnd) {
            task.run();
        }
        doOnTransitionEnd.clear();
        isReadyToRender = true;
    }

    public void setParentTransitionTracker(@Nullable TransitionTracker parentTransitionTracker) {
        this.parentTransitionTracker = parentTransitionTracker;
    }

    private void onReadyToRenderInner(Runnable task) {
        if (isReadyToRender) {
            task.run();
            return;
        }
        doOnTransitionEnd.add(task);
    }

    @Override
    public void onReadyToRender(@NonNull final Runnable task) {
        TransitionTracker p = parentTransitionTracker;
        if (p != null) {
            p.onReadyToRender(new Runnable() {
                @Override
                public void run() {
                    onReadyToRenderInner(task);
                }
            });
            return;
        }
        onReadyToRenderInner(task);
    }
}
