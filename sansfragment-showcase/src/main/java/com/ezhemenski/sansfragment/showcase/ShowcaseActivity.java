package com.ezhemenski.sansfragment.showcase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ezhemenski.sansfragment.Navigator;
import com.ezhemenski.sansfragment.ViewHolder;
import com.ezhemenski.sansfragment.ViewStackFrameLayout;

import java.util.Collections;

import static com.ezhemenski.sansfragment.Transitions.FADE;

public class ShowcaseActivity extends AppCompatActivity {

    enum Screen {ONE, TWO, THREE}

    private Navigator<Screen> navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_showcase);
        ViewStackFrameLayout container = findViewById(R.id.container);
        navigator = container.initViewStack(
                Collections.singleton(Screen.ONE),
                (c, screen, navigator) -> {
                    switch (screen) {
                        default:
                        case ONE:
                            return new OneViewHolder(c, navigator);
                        case TWO:
                            return new TwoViewHolder(c, navigator);
                        case THREE:
                            return new ThreeViewHolder(c);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (!navigator.goBack(FADE)) {
            super.onBackPressed();
        }
    }

    public static class OneViewHolder extends LoggingViewHolder {

        OneViewHolder(@NonNull ViewStackFrameLayout container, @NonNull Navigator<Screen> navigator) {
            super(container, R.layout.view_one);
            rootView.setOnClickListener(v -> navigator.push(Screen.TWO, FADE));
        }
    }

    public static class TwoViewHolder extends LoggingViewHolder {

        TwoViewHolder(@NonNull ViewStackFrameLayout container, @NonNull Navigator<Screen> navigator) {
            super(container, R.layout.view_two);
            rootView.setOnClickListener(v -> navigator.push(Screen.THREE, FADE));
        }
    }

    public static class ThreeViewHolder extends LoggingViewHolder {

        ThreeViewHolder(@NonNull ViewStackFrameLayout container) {
            super(container, R.layout.view_three);
        }
    }

    public static class LoggingViewHolder extends ViewHolder {

        LoggingViewHolder(@NonNull ViewStackFrameLayout container, int layoutRes) {
            super(container, layoutRes);
        }

        @Override
        protected void onAttach() {
            super.onAttach();
            Log.i(this.getClass().getSimpleName(), "onAttach");
        }

        @Override
        protected void onEnterTransitionEnd() {
            super.onEnterTransitionEnd();
            Log.i(this.getClass().getSimpleName(), "onEnterTransitionEnd()");
        }

        @Override
        protected void onDetach() {
            super.onDetach();
            Log.i(this.getClass().getSimpleName(), "onDetach()");
        }
    }
}
