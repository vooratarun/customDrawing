package voora.com.customcalc.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import voora.com.customcalc.R;

/**
 * Created by tarun on 12/16/17.
 */

public class DrawableTextView extends AppCompatTextView {

    public DrawableTextView(Context context) {
        super(context);
        init(context,null);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {

        if(attributeSet != null) {
           TypedArray a = context.obtainStyledAttributes(attributeSet,R.styleable.CompatTextView);

           int leftRes = a.getResourceId(R.styleable.CompatTextView_drawableStart,0);
           int topRes  = a.getResourceId(R.styleable.CompatTextView_drawableTop,0);
           int rightRes  = a.getResourceId(R.styleable.CompatTextView_drawableEnd,0);
           int bottomRes  = a.getResourceId(R.styleable.CompatTextView_drawableBottom,0);

           setDrawables(leftRes,topRes,rightRes,bottomRes);

            a.recycle();
        }
    }

    private void setDrawables(int leftRes, int topRes, int rightRes, int bottomRes) {

        Drawable[]  compoundDrawables = getCompoundDrawables();

        Drawable left = leftRes != 0 ? ContextCompat.getDrawable(getContext(),leftRes)
                            : compoundDrawables[0];
        Drawable top = topRes != 0 ? ContextCompat.getDrawable(getContext(),topRes)
                            : compoundDrawables[1];

        Drawable right = rightRes != 0 ? ContextCompat.getDrawable(getContext(),rightRes)
                            : compoundDrawables[2];
        Drawable bottom = bottomRes != 0 ? ContextCompat.getDrawable(getContext(),bottomRes)
                            : compoundDrawables[3];

        setCompoundDrawables(left,top,right,bottom);

    }

}
