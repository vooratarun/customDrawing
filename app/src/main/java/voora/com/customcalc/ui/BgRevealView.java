package voora.com.customcalc.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by tarun on 12/17/17.
 */

public class BgRevealView extends View {

    private final int STATE_NOT_STARTED = 0;
    private final int STATE_FILL_STARTED = 1;
    private final int STATE_FINISHED = 2;

    private int state = STATE_NOT_STARTED;

    private static final Interpolator INTERPOLATOR = new AccelerateDecelerateInterpolator();

    private static final int FULL_TIME = 800;

    private int startLocationX;
    private int startLocationY;

    private Paint fillPaint;
    private float currentRadius;
    private ObjectAnimator revealAnimator;


    public BgRevealView(Context context) {
        super(context);
        init();
    }

    public BgRevealView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BgRevealView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BgRevealView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
        fillPaint = new Paint();
        fillPaint.setColor(Color.GREEN);
        fillPaint.setStyle(Paint.Style.FILL);
    }

    public void startFromLocation( int[] locationOnscreen) {
        changeState(STATE_FILL_STARTED);
        startLocationX = locationOnscreen[0];
        startLocationY = locationOnscreen[1];

        revealAnimator = ObjectAnimator
                .ofFloat(this,"currentRadius",0,getWidth() + getHeight())
                .setDuration(FULL_TIME);

        revealAnimator.setInterpolator(INTERPOLATOR);
        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startDoneState();
            }
        });
        revealAnimator.start();

    }

    private void startDoneState() {
        changeState(STATE_FINISHED);
        invalidate();
    }

    private void changeState(int state) {
        if(this.state == state) return;
        this.state = state;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if(state == STATE_FINISHED) {
            canvas.drawRect(0,0,getWidth(),getHeight(),fillPaint);
        } else {
            canvas.drawCircle(startLocationX,startLocationY,currentRadius,fillPaint);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public float getCurrentRadius() {
        return currentRadius;
    }

    public void setCurrentRadius(float currentRadius) {
        this.currentRadius = currentRadius;
        invalidate();
    }
}
