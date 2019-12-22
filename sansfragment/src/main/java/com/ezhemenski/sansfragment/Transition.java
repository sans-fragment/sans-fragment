package com.ezhemenski.sansfragment;

import android.view.View;

import androidx.annotation.NonNull;

public interface Transition {
    @NonNull
    TransitionPerformer create(@NonNull View oldView, @NonNull View newView);
}
