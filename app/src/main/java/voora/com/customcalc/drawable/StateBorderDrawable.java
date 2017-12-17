package voora.com.customcalc.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by tarun on 12/17/17.
 */

public class StateBorderDrawable extends Drawable {


    int color;
    Paint paint;

    RectF rectF;
    int borderWidth;
    int borderRadius;

    Path path;

    ColorStateList colorStateList;

    public StateBorderDrawable(int borderRadius, int borderWidth,ColorStateList colorStateList) {

        this.borderRadius = borderRadius;
        this.borderWidth = borderWidth;
        this.colorStateList = colorStateList;
        this.color = colorStateList.getDefaultColor();


        rectF = new RectF();
        path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);

    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        path.reset();
        path.addRect(bounds.left,bounds.top,bounds.right,bounds.bottom, Path.Direction.CW);
        rectF.set(bounds.left+borderWidth,bounds.top+borderWidth,
                bounds.right- borderWidth,bounds.bottom - borderWidth);
        path.addRoundRect(rectF,borderRadius,borderRadius, Path.Direction.CW);

    }

    @Override
    protected boolean onStateChange(int[] state) {
        int stateColor = colorStateList.getColorForState(state,color);
        if(stateColor != color) {
            color = stateColor;
            invalidateSelf();
            return true;
        }

        return false;
    }

    @Override
    public boolean isStateful() {
        return true;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawPath(path,paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
