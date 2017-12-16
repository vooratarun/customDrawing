package voora.com.customcalc.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tarun on 12/16/17.
 */

public class CircularRevealView extends ViewGroup {

    View inkView;
    private static final String TAG = "CircularRevealView";

    public CircularRevealView(Context context) {
        super(context);
        init(context);
    }

    public CircularRevealView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircularRevealView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {

        if(isInEditMode()) return;

        inkView = new View(context);
        addView(inkView);

        ShapeDrawable circle = new ShapeDrawable(new OvalShape());
        circle.getPaint().setColor(Color.GREEN);
        inkView.setBackground(circle);
        inkView.setVisibility(VISIBLE);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
       inkView.layout(l,t,l+ inkView.getMeasuredWidth(), t + inkView.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);

        final float circleSize = (float) Math.sqrt(width * width + height * height) * 2f;

        Log.d("circle size", width +"  " + height + "  " + circleSize);
        final int size = (int) (circleSize / 8f);
        final int sizeSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        inkView.measure(sizeSpec, sizeSpec);
    }


    /**
     * Method to start the animation..it basically scalesOut the view to reveal.
     * @param x  required to translate view to new x position
     * @param y  required to translate view to new y position
     * @param radius to calculate the initial scale.
     */
    public void revealView(int x ,int y, int radius) {

        float startScale = radius * 2f / inkView.getHeight();
        float finalScale =  calculateScale(x,y) * 8f;

        Log.d(TAG, String.format("x %d y %d %f ",x,y,startScale));

        // ScaleDown the view
        prepareView(inkView,x,y,startScale);

        //Scale to max to show the view.
        inkView.animate()
            .scaleX(finalScale)
            .scaleY(finalScale)
            .setDuration(500)
            .start();

    }

    /**
     * Translate the view to clicked position and scaledown the inkView in viewgroup.
     * @param view
     * @param x
     * @param y
     * @param startScale
     */
    private void prepareView(View view ,int x, int y , float startScale) {

        int centerX = view.getWidth() /2;
        int centerY = view.getHeight() /2;
        view.setTranslationX(x - centerX);
        view.setTranslationY(y - centerY);
        view.setPivotX(centerX);
        view.setPivotY(centerY);
        view.setScaleX(startScale);
        view.setScaleY(startScale);
    }

    private float calculateScale(int x, int y) {
        final float centerX = getWidth() / 2f;
        final float centerY = getHeight() / 2f;
        final float maxDistance = (float) Math.sqrt(centerX * centerX + centerY * centerY);

        final float deltaX = centerX - x;
        final float deltaY = centerY - y;
        final float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        final float scale = 0.5f + (distance / maxDistance) * 0.5f;
        return scale;
    }
}
