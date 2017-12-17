package voora.com.customcalc.drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by tarun on 12/17/17.
 */

@SuppressLint("AppCompatCustomView")
public class BorderImageView extends ImageView {

    BorderDrawable borderDrawable;

    public BorderImageView(Context context) {
        super(context);
        init();
    }

    public BorderImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BorderImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BorderImageView(Context context,
                            @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        borderDrawable = new BorderDrawable(getPaddingLeft(),getPaddingLeft()/2);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        borderDrawable.setBounds(0,0,w,h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        borderDrawable.draw(canvas);
    }
}
