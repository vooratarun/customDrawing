package voora.com.customcalc.drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by tarun on 12/17/17.
 */

@SuppressLint("AppCompatCustomView")
public class StateBorderImageView extends ImageView {

    StateBorderDrawable borderDrawable;


    public StateBorderImageView(Context context) {
        super(context);
        init();
    }

    public StateBorderImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StateBorderImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public StateBorderImageView(Context context, @Nullable AttributeSet attrs,
                                int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        int [][] states = new int[][] {
                {-android.R.attr.state_pressed},
                {android.R.attr.state_pressed}
        };

        int[] colors = new int[] {
                Color.BLUE,Color.GREEN
        };

        ColorStateList colorStateList = new ColorStateList(states,colors);

        borderDrawable = new
                StateBorderDrawable(getPaddingLeft() /2, getPaddingLeft(),colorStateList);
        borderDrawable.setCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        borderDrawable.draw(canvas);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        borderDrawable.setState(getDrawableState());
    }

    @Override
    protected boolean verifyDrawable(@NonNull Drawable dr) {
        return super.verifyDrawable(dr) || dr == borderDrawable;
    }
}
