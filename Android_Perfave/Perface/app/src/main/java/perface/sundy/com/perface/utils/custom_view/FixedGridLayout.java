package perface.sundy.com.perface.utils.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sundy on 15/6/29.
 */
public class FixedGridLayout extends ViewGroup {

    private int mCellWidth;
    private int mCellHeight;
    private int mCountpuls;
    private final static int VIEW_MARGIN = 10;
    private int mCelllong;

    public FixedGridLayout(Context context) {
        super(context);
    }

    public FixedGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化单元格宽和高
        mCellWidth = 60;
        mCellHeight = 70;
        mCelllong = 200;
        mCountpuls = 1;
    }

    public void setCountpuls(int w) {
        mCountpuls = w;
    }

    public void setmCelllong(int listlong) {
        mCelllong = listlong;
    }

    //设置单元格宽度
    public void setCellWidth(int w) {
        mCellWidth = w;
        requestLayout();
    }

    //设置单元格高度
    public void setCellHeight(int h) {
        mCellHeight = h;
        requestLayout();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        //获取布局控件宽高
        int width = getWidth();
        int height = getHeight();
        //创建画笔
        Paint mPaint = new Paint();
        //设置画笔的各个属性
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(0);
        mPaint.setAntiAlias(true);

        //创建矩形框
        Rect mRect = new Rect(0, 0, width, height);
        //绘制边框
        canvas.drawRect(mRect, mPaint);

        //最后必须调用父类的方法
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //创建测量参数
        int cellWidthSpec = MeasureSpec.makeMeasureSpec(mCellWidth, MeasureSpec.AT_MOST);
        int cellHeightSpec = MeasureSpec.makeMeasureSpec(mCellHeight, MeasureSpec.AT_MOST);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeigth = MeasureSpec.getSize(heightMeasureSpec);


        //记录ViewGroup中Child的总个数
        int count = getChildCount();

        //设置子空间Child的宽高
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int widthSpec = 0;
            int heightSpec = 0;
            LayoutParams params = childView.getLayoutParams();

            if (params.width > 0) {
                widthSpec = MeasureSpec.makeMeasureSpec(params.width, MeasureSpec.EXACTLY);
            } else if (params.width == -1) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.EXACTLY);
            } else if (params.width == -2) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.AT_MOST);
            }

            if (params.height > 0) {
                heightSpec = MeasureSpec.makeMeasureSpec(params.height, MeasureSpec.EXACTLY);
            } else if (params.height == -1) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeigth, MeasureSpec.EXACTLY);
            } else if (params.height == -2) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeigth, MeasureSpec.AT_MOST);
            }
            childView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }

        //设置容器控件所占区域大小
        //注意setMeasuredDimension和resolveSize的用法
        setMeasuredDimension(resolveSize(mCellWidth, widthMeasureSpec), resolveSize(mCellHeight * mCountpuls, heightMeasureSpec));
        //setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

        //不需要调用父类的方法
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int row = 0;// which row lay you view relative to parent
        int lengthX = l;    // right position of child relative to parent
        int lengthY = t;    // bottom position of child relative to parent
        for (int i = 0; i < count; i++) {

            final View child = this.getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (i == 0) {
                lengthX = width;
            } else {
                lengthX += width + VIEW_MARGIN;
            }

            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height + t;

            //if it can't drawing on a same line , skip to next line
            if (lengthX > r - mCelllong) {
                lengthX = width;
                row++;
                lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height + t;
            }

            child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
        }
    }
}
