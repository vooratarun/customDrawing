package voora.com.customcalc.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
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

public class BorderDrawable extends Drawable {

    Paint paint;
    int color;
    int borderWidth;
    int borderRadius;


    RectF rectF;
    Path path;

    public BorderDrawable(int borderWidth,int borderRadius) {
        paint =  new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        rectF = new RectF();

        color = Color.GREEN;
        paint.setColor(color);

        this.borderRadius = borderRadius;
        this.borderWidth = borderWidth;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        path.reset();
        path.addRect(bounds.left,bounds.top,bounds.right,bounds.bottom, Path.Direction.CW);
        rectF.set(bounds.left + borderWidth , bounds.top + borderWidth , bounds.right - borderWidth,
                    bounds.bottom - borderWidth);
        path.addRoundRect(rectF,borderRadius,borderRadius, Path.Direction.CW);

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
