package com.ezhemenski.sansfragment;

public interface TransitionTracker {
    void onReadyToRender(Runnable task);
}
