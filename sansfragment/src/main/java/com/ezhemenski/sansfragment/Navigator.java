package com.ezhemenski.sansfragment;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;

@SuppressWarnings("unused")
public interface Navigator<S> {
    @MainThread
    void push(@NonNull S screen, @Nullable Transition transition);

    @MainThread
    void set(@NonNull S screen, @Nullable Transition transition);

    @MainThread
    void set(@NonNull Collection<S> screens, @Nullable Transition transition);

    @MainThread
    boolean goBack(@Nullable Transition transition);

    @MainThread
    void push(@NonNull S screen);

    @MainThread
    void set(@NonNull S screen);

    @MainThread
    void set(@NonNull Collection<S> screens);

    @MainThread
    boolean goBack();

    @MainThread
    @NonNull
    Collection<S> getStackSnapshot();

    @Nullable
    S getTop();

    void skipTransition();
}
