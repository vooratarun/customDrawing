package voora.com.customcalc.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import voora.com.customcalc.R;


public class CircleImage extends View {

	private Paint circlePaint;
	private int centerX;
	private int centerY;
	private int radius;


	public CircleImage(Context context) {
		super(context);
		init(context,null);
	}

	public CircleImage(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}

	public CircleImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context,attrs);
	}

	public CircleImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context,attrs);
	}


	void init(Context context,AttributeSet attributeSet){
		circlePaint = new Paint();
		circlePaint.setStyle(Paint.Style.FILL);

		int color = Color.GRAY;
		if(attributeSet != null){
			TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.View);
			color = a.getColor(R.styleable.View_background,color);
			a.recycle();
		}

		circlePaint.setColor(color);
	}


	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(centerX,centerY,radius,circlePaint);
		super.draw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		centerX = w /2;
		centerY = h/2;
		radius = Math.min(w,h)/2;
	}


}