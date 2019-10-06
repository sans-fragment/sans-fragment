package com.ezhemenski.sansfragment;

import android.view.View;

class Utils {
    static void doOnAttach(View view, Runnable task) {
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                view.removeOnAttachStateChangeListener(this);
                task.run();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });
    }
}
