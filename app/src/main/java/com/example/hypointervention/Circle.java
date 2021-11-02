package com.example.hypointervention;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Circle extends View {
    Paint paint;
    Path path;

    public Circle(Context context) {
        super(context);
        init();
    }
    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public Circle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public int getColor(Context context) {
        int col = ContextCompat.getColor(context, R.color.red);
        return col;
    }
    private void init(){
        int col = getColor(getContext());
        paint = new Paint();
        paint.setColor(col);


    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        int mRadius;

        paint.setStyle(Paint.Style.FILL);

        mRadius = getHeight() < getWidth() ? getHeight() : getWidth();
        mRadius = (int) (mRadius * 0.65 / 2);

        canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, mRadius, paint);
        //drawCircle(cx, cy, radius, paint)
    }
}
