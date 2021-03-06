
package voora.com.customcalc.ui;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Interpolator;

public class FabAnimationUtils {

    private static final long DEFAULT_DURATION = 300L;
    private static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();

    public static void scaleIn(final View fab) {
        scaleIn(fab, DEFAULT_DURATION, null);
    }

    public static void scaleIn(final View fab, long duration, final ScaleCallback callback) {
        fab.setVisibility(View.VISIBLE);
        ViewCompat.animate(fab)
                .scaleX(1.0F)
                .scaleY(1.0F)
                .alpha(1.0F)
                .setDuration(duration)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    public void onAnimationStart(View view) {
                        if (callback != null) callback.onAnimationStart();
                    }

                    public void onAnimationCancel(View view) {
                    }

                    public void onAnimationEnd(View view) {
                        view.setVisibility(View.VISIBLE);
                        if (callback != null) callback.onAnimationEnd();
                    }
                }).start();
    }


    public static void scaleOut(final View fab, long duration, final ScaleCallback callback) {
        ViewCompat.animate(fab)
                .scaleX(0.0F)
                .scaleY(0.0F)
                .alpha(0.0F)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setDuration(duration)
                .withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    public void onAnimationStart(View view) {
                        if (callback != null) callback.onAnimationStart();
                    }

                    public void onAnimationCancel(View view) {
                    }

                    public void onAnimationEnd(View view) {
                        view.setVisibility(View.INVISIBLE);
                        if (callback != null) callback.onAnimationEnd();
                    }
                }).start();
    }

    public interface ScaleCallback {
        public void onAnimationStart();

        public void onAnimationEnd();
    }


}
