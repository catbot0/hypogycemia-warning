package com.example.hypointervention;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Circle extends View {
    Paint paintFill;
    Paint paintStroke;
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

    public int getColorFill(Context context) {
        int col = ContextCompat.getColor(context, R.color.black);
        return col;
    }

    public int getColorStroke(Context context) {
        int col = ContextCompat.getColor(context, R.color.voice_assistant_light);
        return col;
    }

    private void init(){
        int colFill = getColorFill(getContext());
        paintFill = new Paint();
        paintFill.setColor(colFill);

        int colStroke = getColorStroke(getContext());
        paintStroke = new Paint();
        paintStroke.setColor(colStroke);
        paintStroke.setStrokeWidth(10);


    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        int mRadius;

        paintFill.setStyle(Paint.Style.FILL);
        paintStroke.setStyle(Paint.Style.STROKE);

        mRadius = getHeight() < getWidth() ? getHeight() : getWidth();
        mRadius = (int) (mRadius * 0.60 / 2);

        canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, mRadius, paintFill);
        canvas.drawCircle(this.getWidth()/2+2, this.getHeight()/2+2, mRadius, paintStroke);
        //drawCircle(cx, cy, radius, paint)
    }
}
