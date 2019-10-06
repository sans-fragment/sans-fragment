package com.ezhemenski.sansfragment;

import android.support.annotation.NonNull;
import android.view.View;

public interface Transition {
    @NonNull TransitionPerformer create(@NonNull View oldView, @NonNull View newView);
}
