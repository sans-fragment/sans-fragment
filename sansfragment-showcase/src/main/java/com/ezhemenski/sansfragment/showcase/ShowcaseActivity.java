package com.ezhemenski.sansfragment.showcase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ezhemenski.sansfragment.Navigator;
import com.ezhemenski.sansfragment.TransitionTracker;
import com.ezhemenski.sansfragment.ViewHolder;
import com.ezhemenski.sansfragment.ViewStackFrameLayout;

import static com.ezhemenski.sansfragment.Transitions.FADE;
import static com.ezhemenski.sansfragment.Transitions.SLIDE_IN;
import static com.ezhemenski.sansfragment.Transitions.SLIDE_OUT;

public class ShowcaseActivity extends AppCompatActivity
        implements ViewStackFrameLayout.Adapter<ShowcaseActivity.Screen>, TransitionTracker {

    enum Screen {ONE, TWO, THREE}

    private Navigator<Screen> navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_showcase);
        ViewStackFrameLayout container = findViewById(R.id.container);
        navigator = container.initViewStack(Screen.ONE, this);
    }

    @NonNull
    @Override
    public ViewHolder createViewHolder(@NonNull ViewStackFrameLayout container, @NonNull Screen screen, @NonNull Navigator<Screen> navigator) {
        switch (screen) {
            default:
            case ONE:
                return new OneViewHolder(container, navigator);
            case TWO:
                return new TwoViewHolder(container, navigator);
            case THREE:
                return new ThreeViewHolder(container, this);
        }
    }

    @Override
    public void onBackPressed() {
        if (!navigator.goBack(SLIDE_OUT)) {
            super.onBackPressed();
        }
    }

    @Override
    public void onReadyToRender(Runnable task) {
        task.run();
    }

    public static class OneViewHolder extends LoggingViewHolder implements View.OnClickListener {

        private final Navigator<Screen> navigator;

        OneViewHolder(@NonNull ViewStackFrameLayout container, @NonNull Navigator<Screen> navigator) {
            super(container, R.layout.view_one);
            this.navigator = navigator;
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            navigator.push(Screen.TWO, FADE);
        }
    }

    public static class TwoViewHolder extends LoggingViewHolder implements View.OnClickListener {

        private final Navigator<Screen> navigator;

        TwoViewHolder(@NonNull ViewStackFrameLayout container, @NonNull Navigator<Screen> navigator) {
            super(container, R.layout.view_two);
            this.navigator = navigator;
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            navigator.push(Screen.THREE, SLIDE_IN);
        }
    }

    public static class ThreeViewHolder extends LoggingViewHolder {

        ThreeViewHolder(@NonNull ViewStackFrameLayout container, TransitionTracker parentTransitionTracker) {
            super(container, R.layout.view_three);
            setParentTransitionTracker(parentTransitionTracker);
        }
    }

    public static class LoggingViewHolder extends ViewHolder {

        private  String tag = this.getClass().getSimpleName();

        LoggingViewHolder(@NonNull ViewStackFrameLayout container, int layoutRes) {
            super(container, layoutRes);
        }

        @Override
        protected void onAttach() {
            super.onAttach();
            Log.i(tag, "onAttach()");
            onReadyToRender(new Runnable() {
                @Override
                public void run() {
                    Log.i(tag, "I'm ready to render!");
                }
            });
        }

        @Override
        protected void onEnterTransitionEnd() {
            super.onEnterTransitionEnd();
            Log.i(tag, "onEnterTransitionEnd()");
        }

        @Override
        protected void onDetach() {
            super.onDetach();
            Log.i(tag, "onDetach()");
        }
    }
}
