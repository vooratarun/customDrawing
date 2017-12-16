package voora.com.customcalc.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

/**
 * Extends the ShapeDrawable and adds CustomText init.
 */
public class TextDrawable extends ShapeDrawable {

    private final Paint textPaint;
    private final int height;
    private final int width;
    String text = "A";
    private final int fontSize;
    int bgColor = Color.GRAY;



    public TextDrawable(RectShape shape) {
        super(shape);

        width = -1;
        height = -1;
        fontSize = -1;

        // text paint settings
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);


        // background  paint bgColor
        Paint paint = getPaint();
        paint.setColor(bgColor);

    }

    /**
     * Adding text to the Shape Drawable added.
     * @param canvas
     */

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect r = getBounds();

        int count = canvas.save();
        canvas.translate(r.left, r.top);

        int width = this.width < 0 ? r.width() : this.width;
        int height = this.height < 0 ? r.height() : this.height;
        int fontSize = this.fontSize < 0 ? (Math.min(width, height) / 2) : this.fontSize;
        textPaint.setTextSize(fontSize);
        canvas.drawText(text, width / 2, height / 2
                - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);

        canvas.restoreToCount(count);

    }


    @Override
    public void setAlpha(int alpha) {
        textPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        textPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return width;
    }

    @Override
    public int getIntrinsicHeight() {
        return height;
    }

}