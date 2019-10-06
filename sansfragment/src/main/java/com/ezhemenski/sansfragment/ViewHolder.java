package com.ezhemenski.sansfragment;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

public class ViewHolder {

    protected final View rootView;

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
    }

    @CallSuper
    protected void onEnterTransitionEnd() {
    }
}
