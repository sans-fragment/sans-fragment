package com.ezhemenski.sansfragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

class ViewStackController<S> implements Navigator<S> {

    private final Adapter<S> adapter;
    private final TransitionManager transitionManager;
    private final Deque<S> stack = new ArrayDeque<>();

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
    public Collection<S> stackSnapshot() {
        return new ArrayList<>(stack);
    }

    private void putTopScreen(Transition transition) {
        if (stack.isEmpty()) {
            return;
        }
        ViewHolder viewHolder = adapter.createViewHolder(stack.peek(), this);
        transitionManager.putView(viewHolder.rootView, transition, viewHolder::onEnterTransitionEnd);
    }
}
