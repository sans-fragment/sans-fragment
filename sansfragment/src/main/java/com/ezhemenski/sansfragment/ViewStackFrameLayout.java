package com.ezhemenski.sansfragment;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

@SuppressWarnings("unused")
public class ViewStackFrameLayout extends FrameLayout {

    public interface Adapter<S> {
        @NonNull
        ViewHolder createViewHolder(
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

    @MainThread
    public <S> Navigator<S> initViewStack(@NonNull S initialScreen, final Adapter<S> adapter) {
        Navigator<S> navigator = setAdapter(adapter);
        navigator.push(initialScreen);
        return navigator;
    }

    @MainThread
    public <S> Navigator<S> setAdapter(final Adapter<S> adapter) {
        removeAllViews();
        return new ViewStackController<>(
                new com.ezhemenski.sansfragment.Adapter<S>() {
                    @NonNull
                    @Override
                    public ViewHolder createViewHolder(@NonNull S screen, @NonNull Navigator<S> navigator) {
                        return adapter.createViewHolder(ViewStackFrameLayout.this, screen, navigator);
                    }
                },
                container
        );
    }
}
