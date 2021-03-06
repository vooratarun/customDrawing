package voora.com.customcalc.ui;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

class BadgeCircle {

    @NonNull
    static ShapeDrawable make(@IntRange(from = 0) int size, @ColorInt int color) {
        ShapeDrawable indicator = new ShapeDrawable(new OvalShape());
        indicator.setIntrinsicWidth(size);
        indicator.setIntrinsicHeight(size);
        indicator.getPaint().setColor(color);
        return indicator;
    }

}