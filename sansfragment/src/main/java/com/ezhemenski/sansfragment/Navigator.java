package com.ezhemenski.sansfragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;

@SuppressWarnings("unused")
public interface Navigator<S> {
    void push(@NonNull S screen, @Nullable Transition transition);
    void set(@NonNull S screen, @Nullable Transition transition);
    void set(@NonNull Collection<S> screens, @Nullable Transition transition);
    boolean goBack(@Nullable Transition transition);
    void push(@NonNull S screen);
    void set(@NonNull S screen);
    void set(@NonNull Collection<S> screens);
    boolean goBack();
    @NonNull Collection<S> stackSnapshot();
}
