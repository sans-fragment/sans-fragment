package com.ezhemenski.sansfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

class ViewStackController<S> implements Navigator<S> {

    private final Adapter<S> adapter;
    private final TransitionManager transitionManager;
    private final Deque<S> stack = new ArrayDeque<>();
    private ViewHolder currentViewHolder = null;

    ViewStackController(Adapter<S> adapter, TransitionManager.Container container) {
        this.adapter = adapter;
        this.transitionManager = new TransitionManager(container);
    }

    @Override
    public void push(@NonNull S screen, @Nullable Transition transition) {
        stack.push(screen);
        putTopScreen(transition);
    }

    @Override
    public void set(@NonNull S screen, @Nullable Transition transition) {
        stack.clear();
        stack.push(screen);
        putTopScreen(transition);
    }

    @Override
    public void set(@NonNull Collection<S> screens, @Nullable Transition transition) {
        stack.clear();
        stack.addAll(screens);
        putTopScreen(transition);
    }

    @Override
    public boolean goBack(@Nullable Transition transition) {
        if (stack.size() < 2) {
            return false;
        }
        stack.pop();
        putTopScreen(transition);
        return true;
    }

    @Override
    public void push(@NonNull S screen) {
        push(screen, null);
    }

    @Override
    public void set(@NonNull S screen) {
        set(screen, null);
    }

    @Override
    public void set(@NonNull Collection<S> screens) {
        set(screens, null);
    }

    @Override
    public boolean goBack() {
        return goBack(null);
    }

    @NonNull
    @Override
    public Collection<S> getStackSnapshot() {
        return new ArrayList<>(stack);
    }

    @Nullable
    @Override
    public S getTop() {
        return stack.peek();
    }

    private void putTopScreen(Transition transition) {
        if (stack.isEmpty()) {
            return;
        }
        if (currentViewHolder != null) {
            currentViewHolder.isReadyToRender = false;
        }
        final ViewHolder viewHolder = adapter.createViewHolder(stack.peek(), this);
        currentViewHolder = viewHolder;
        transitionManager.putView(viewHolder.rootView, transition, new Runnable() {
            @Override
            public void run() {
                viewHolder.onEnterTransitionEnd();
            }
        });
    }

    @Override
    public void skipTransition() {
        transitionManager.skip();
    }
}
