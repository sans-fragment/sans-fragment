package com.ezhemenski.sansfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("unused")
public class ViewStackFrameLayout extends FrameLayout {

    public interface Adapter<S> {
        @NonNull ViewHolder createViewHolder(
                @NonNull ViewStackFrameLayout container,
                @NonNull S screen,
                @NonNull Navigator<S> navigator
        );
    }

    private final TransitionManager.Container container = new TransitionManager.Container() {
        @Nullable
        @Override
        public View getCurrentView() {
            if (getChildCount() == 0) {
                return null;
            }
            return getChildAt(getChildCount() - 1);
        }

        @Override
        public void addView(@NonNull View view) {
            ViewStackFrameLayout.this.addView(view);
        }

        @Override
        public void removeView(@NonNull View view) {
            ViewStackFrameLayout.this.removeView(view);
        }
    };

    public ViewStackFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public ViewStackFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewStackFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public <S> Navigator<S> initViewStack(@NonNull Collection<S> initialStack, Adapter<S> adapter) {
        removeAllViews();
        ViewStackController<S> controller = new ViewStackController<>(
                (screen, navigator) ->
                        adapter.createViewHolder(ViewStackFrameLayout.this, screen, navigator),
                container
        );
        controller.set(initialStack);
        return controller;
    }

    public <S> Navigator<S> setAdapter(Adapter<S> adapter) {
        return initViewStack(Collections.emptyList(), adapter);
    }
}
