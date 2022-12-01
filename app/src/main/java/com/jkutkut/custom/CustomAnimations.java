package com.jkutkut.custom;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.widget.Button;


public class CustomAnimations {
    /**
     * Changes the background of the view by the given color when the user touches it.
     * @param btn The button to change its background color.
     * @param color The color to change the background to.
     */
    @SuppressLint("ClickableViewAccessibility")
    protected static void setBtnClickFeedback(Button btn, int color) {
        btn.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    v.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                    v.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    v.getBackground().clearColorFilter();
                    v.invalidate();
                    break;
                }
            }
            return false;
        });
    }
}
